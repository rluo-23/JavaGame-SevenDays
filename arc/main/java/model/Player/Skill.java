package arc.main.java.model.Player;

public class Skill {
    int huntSkill;
    int collectSkill;
    int capacity;

    public Skill() {
        huntSkill = 1;
        collectSkill = 1;
        capacity = 6;
    }

    public Skill(int huntSkill, int collectSkill, int capacity) {
        this.huntSkill = huntSkill;
        this.collectSkill = collectSkill;
        this.capacity = capacity;
    }

    public void setHuntSkill(int huntSkill) {
        this.huntSkill = huntSkill;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCollectSkill(int collectSkill) {
        this.collectSkill = collectSkill;
    }

    public int getHuntSkill() {
        return huntSkill;
    }

    public void addHuntSkill() {
        this.huntSkill++;
    }

    public int getCollectSkill() {
        return collectSkill;
    }

    public void addCollectSkill() {
        this.collectSkill++;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addCapacity() {
        this.capacity = this.capacity + 4;
    }

}
