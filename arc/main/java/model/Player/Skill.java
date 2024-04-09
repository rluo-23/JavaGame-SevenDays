package arc.main.java.model.Player;

public class Skill {
    int huntSkill;
    int collectSkill;
    int capacity;

    public Skill(){
        huntSkill = 1;
        collectSkill = 1;
        capacity = 3;
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
        this.capacity++;
    }

}
