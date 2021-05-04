public class Pod {
    //定义pod所需要的字段
    private final int PodId; //唯一的id，自增的
    private int CPU_L;
    private int CPU_R;
    private int MEM_L;
    private int MEM_R;
    private int queue;
    private int CPU_avg;
    private int MEM_avg;
    private double score; //得分，划分优先级队列时用

    public Pod(int PodId) {
        this.PodId = PodId;
    }
    public void setScore(double score){
        this.score = score;
    }
    public double getScore(){
        return score;
    }
    public void setCPU_L(int CPU_L){
        this.CPU_L = CPU_L;
    }
    public void setCPU_R(int CPU_R){
        this.CPU_R = CPU_L;
    }
    public void setMEM_L(int MEM_L){
        this.MEM_L = MEM_L;
    }
    public void setMEM_R(int MEM_R){
        this.MEM_R = MEM_R;
    }
    public void setQueue(int queue){
        this.queue = queue;
    }
    public int getCPU_L(){
        return CPU_L;
    }
    public int getCPU_R(){
        return CPU_R;
    }
    public int getMEM_L(){
        return MEM_L;
    }
    public int getMEM_R(){
        return MEM_R;
    }
    public int getQueue(){
        return queue;
    }
    public int getCPU_avg(){
        return (CPU_L+CPU_R)/2;
    }
    public int getMEM_avg(){
        return (MEM_L+MEM_R)/2;
    }

}
