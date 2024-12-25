package monster;

public interface Monster {
    int getHpRatio();
    void beAttacked(long damage);
    boolean isAlive();
    void levelUp();
    long yieldGold();
}
