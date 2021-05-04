public class Node {
    private int CPU_total;
    private int MEM_total;
    private int CPU_used=0;
    private int MEM_used=0;
    private int queue_one=0;
    private int queue_two=0;
    private int queue_three=0;
    private int queue_four=0;
    private double score=0;

    public int getCPU_total() {
        return CPU_total;
    }

    public double getScore() {
        return score;
    }

    public int getCPU_used() {
        return CPU_used;
    }

    public int getMEM_total() {
        return MEM_total;
    }

    public int getMEM_used() {
        return MEM_used;
    }

    public int getQueue_four() {
        return queue_four;
    }

    public int getQueue_one() {
        return queue_one;
    }

    public int getQueue_three() {
        return queue_three;
    }

    public int getQueue_two() {
        return queue_two;
    }

    public void setCPU_total(int CPU_total) {
        this.CPU_total = CPU_total;
    }

    public void setCPU_used(int CPU_used) {
        this.CPU_used = CPU_used;
    }

    public void setMEM_used(int MEM_used) {
        this.MEM_used = MEM_used;
    }

    public void setMEM_total(int MEM_total) {
        this.MEM_total = MEM_total;
    }

    public void setQueue_four(int queue_four) {
        this.queue_four = queue_four;
    }

    public void setQueue_one(int queue_one) {
        this.queue_one = queue_one;
    }

    public void setQueue_three(int queue_three) {
        this.queue_three = queue_three;
    }

    public void setQueue_two(int queue_two) {
        this.queue_two = queue_two;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
