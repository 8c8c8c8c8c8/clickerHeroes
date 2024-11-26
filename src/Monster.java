public interface Monster {
    long getMAX_HP();
    void beAttacked(long damage);
    boolean isAlive();
    Monster levelUp();
}
