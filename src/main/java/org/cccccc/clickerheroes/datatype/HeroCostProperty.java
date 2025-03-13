package org.cccccc.clickerheroes.datatype;

public class HeroCostProperty extends ExpExprProperty {

    public HeroCostProperty(String name) {
        super(name);
    }

    public HeroCostProperty(String name, String value) {
        super(String.format("%sCost", name), value);
    }

    public HeroCostProperty(String name, int val) {
        super(name, val);
    }

    private ExpExprProperty getCostIncRatio() {
        double costIncRatio = 1.07;
        return new ExpExprProperty("costIncRatio", costIncRatio);
    }

    public void levelUp(int level) {
        ExpExprProperty constIncRatio = getCostIncRatio();
        constIncRatio.customPower(level);
        this.customMultiply(constIncRatio);
    }

    private ExpExprProperty calculateCostFactor(int currentLevel, int targetLevel) {
        // r = 1.07, n = targetLevel - currentLevel
        // result = (r ^ n - 1) / (r - 1)
        ExpExprProperty costFactor = getCostIncRatio();
        costFactor.customPower(targetLevel - currentLevel);
        costFactor.real -= 1;
        costFactor.scaling();
        costFactor.customDivide(1.07 - 1);
        return costFactor;
    }

    private ExpExprProperty calculateCostFormula(int currentLevel, int targetLevel) {
        // r = 1.07, n = targetLevel - currentLevel
        // formula =  currentCost * r * (r ^ n - 1) / (r - 1)
        ExpExprProperty cost = getCostIncRatio();
        cost.customMultiply(this);
        ExpExprProperty costFactor = calculateCostFactor(currentLevel, targetLevel);
        cost.customMultiply(costFactor);
        return cost;
    }

    public ExpExprProperty getCost(int currentLevel, int targetLevel) {
        // current cost = floor[BASE_COST * 1.07 ^ (level - 1)]
        if (isCid()) {
            return getCidCost(currentLevel, targetLevel);
        }
        return calculateCostFormula(currentLevel, targetLevel);
    }

    private ExpExprProperty getCidCost(int currentLevel, int targetLevel) {
        // 1 ~ 15 : C = floor[(5+level) * 1.07 ^ (level - 1)]
        // 16 ~ : C = floor[20 * 1.07 ^ (level - 1)]
        int levelBoundary = 16;
        int cidCostConstant = 5;
        int cidCostConstant2 = 20;
        ExpExprProperty cost = new ExpExprProperty("cidCost");
        ExpExprProperty levelCost;
        if (currentLevel < levelBoundary) {
            for (int level = currentLevel; level < Math.min(levelBoundary, targetLevel); level++) {
                levelCost = getCostIncRatio();
                levelCost.customPower(level - 1);
                levelCost.customMultiply(cidCostConstant + level);
                cost.customAdd(levelCost);
            }
        }
        if (targetLevel < levelBoundary) {
            return cost;
        }
        levelCost = calculateCostFactor(levelBoundary, targetLevel);
        levelCost.customMultiply(cidCostConstant2);
        cost.customAdd(levelCost);
        return cost;
    }

    private boolean isCid() {
        return getName().contains("Cid");
    }
}
