import java.util.*;

public class util {
    public  List<Pod> podList = new ArrayList<>();
    Comparator<Pod> comparator = new Comparator<Pod>() {
        @Override
        public int compare(Pod o1, Pod o2) {
            return (int) (o2.getScore()*1000 - o1.getScore()*1000);
        }
    };
    public PriorityQueue<Pod> queue1 = new PriorityQueue<Pod>(comparator);
    public PriorityQueue<Pod> queue2 = new PriorityQueue<Pod>(comparator);
    public PriorityQueue<Pod> queue3 = new PriorityQueue<Pod>(comparator);
    public PriorityQueue<Pod> queue4 = new PriorityQueue<Pod>(comparator);
    private final int ScheduleNum = 50;  //设置允许的调度次数，来代替可以调度的时间
    //对pod进行初始化，返回一个list存储pod的信息
    public void initPod(int n){
        //创建Pod，对Pod进行赋值
        int[] CPU_R_list = {31,11,25,35,49,30,14,44,31,37,26,16,26,18,33,33,11,14,33,33,48,36,27,30,38,37,30,19,25,32,48,34,14,25,10,30,16,42,47,12,23,40,16,25,26,28,31,38,47,26};
        int[] CPU_L_list = {35,27,40,36,61,40,26,53,47,50,28,32,27,34,50,39,17,23,39,45,65,43,35,42,55,50,35,20,40,47,57,52,19,44,20,40,18,57,62,27,34,57,22,25,26,31,37,38,59,33};
        int[] MEM_R_list = {456,387,738,735,419,638,368,605,447,512,262,570,547,440,423,316,407,663,336,615,635,551,448,614,647,585,688,515,277,313,357,540,403,594,511,695,647,737,454,686,405,352,743,694,624,368,380,667,637,312};
        int[] MEM_L_list = {614,615,1004,923,691,934,408,864,505,522,511,838,838,660,450,447,559,933,342,743,696,556,656,655,689,816,815,619,452,600,540,788,686,870,686,822,897,783,574,923,519,365,984,835,636,554,427,670,978,392};
        /*
        * 实现
        * ferz
        * 2021.3.22
        *  **/
        int index = 0;
        for(int i=0;i<n;i++){
            Pod pod = new Pod(i);
            podList.add(pod);
        }
        for(int i=0;i<n;i++){
            podList.get(i).setCPU_R(CPU_R_list[index]);
            podList.get(i).setCPU_L(CPU_L_list[index]);
            podList.get(i).setMEM_L(MEM_L_list[index]);
            podList.get(i).setMEM_R(MEM_R_list[index]);
            index++;
        }
    }
    //给每个Pod进行打分

    //遍历pod，计算平均值，并划分优先级队列
    public void DividedPriorityQueue(int n){
        //遍历list
        int CPU_total=0,MEM_total=0;
        for(int i=0;i<n;i++){
            CPU_total += podList.get(i).getCPU_avg();
            MEM_total += podList.get(i).getMEM_avg();
        }
        int CPU_avg = CPU_total/n;
        int MEM_avg = MEM_total/n;
        //重写比较方法，来比较Pod的大小，进行优先级队列的划分
//        Comparator<Pod> comparator = new Comparator<Pod>() {
//            @Override
//            public int compare(Pod o1, Pod o2) {
//                return (int) (o1.getScore() - o2.getScore());
//            }
//        };
        //优先级队列会自动根据打分来调整，每次取队列头元素即可

        for(int i=0;i<n;i++){
            if(podList.get(i).getCPU_avg()>CPU_avg && podList.get(i).getMEM_avg()>MEM_avg){
                podList.get(i).setQueue(1);
                int size = queue1.size();
                double QS = getQsScore(podList.get(i),CPU_avg,MEM_avg);
                double pod_score = 1-0.06*size+QS;
                podList.get(i).setScore(pod_score);
                queue1.add(podList.get(i));
            }
            else if(podList.get(i).getCPU_avg()>CPU_avg && podList.get(i).getMEM_avg()<MEM_avg){
                podList.get(i).setQueue(2);
                int size = queue2.size();
                double QS = getQsScore(podList.get(i),CPU_avg,MEM_avg);
                double pod_score = 1-0.06*size+QS;
                podList.get(i).setScore(pod_score);
                queue2.add(podList.get(i));
            }
            else if(podList.get(i).getCPU_avg()<CPU_avg && podList.get(i).getMEM_avg()>MEM_avg){
                podList.get(i).setQueue(3);
                int size = queue3.size();
                double QS = getQsScore(podList.get(i),CPU_avg,MEM_avg);
                double pod_score = 1-0.06*size+QS;
                podList.get(i).setScore(pod_score);
                queue3.add(podList.get(i));
            }
            else {
                podList.get(i).setQueue(4);
                int size = queue4.size();
                double QS = getQsScore(podList.get(i),CPU_avg,MEM_avg);
                double pod_score = 1-0.06*size+QS;
                podList.get(i).setScore(pod_score);
                queue4.add(podList.get(i));
            }
        }
    }
    //获取pod的依赖资源稳定性的值
    public double getQsScore(Pod pod,int CPU_avg,int MEM_avg){
        double QS1 = (Math.pow((pod.getCPU_L()-CPU_avg),2)/100+(Math.pow((pod.getCPU_R()-CPU_avg),2))/2)/100;
        double QS2 = (Math.pow((pod.getMEM_L()-MEM_avg),2)/10000+(Math.pow((pod.getMEM_R()-MEM_avg),2))/2)/10000;
        return QS1+QS2;
    }
    //对Node进行初始化赋值
    List<Node> nodeList = new ArrayList<>();
    private final double k = 1;
    private final double p = 1;
    private final double m = 0.5;
    private final double n =1;
    public void initNode(int n){
        int[] node_CPU = {200,200,300,200,400};
        int[] node_MEM = {2500,4500,4500,5500,5500};
        //对node进行赋值
        for(int i=0;i<n;i++){
            Node node = new Node();
            nodeList.add(node);
        }
        for(int i=0;i<n;i++){
            nodeList.get(i).setCPU_total(node_CPU[i]);
            nodeList.get(i).setMEM_total(node_MEM[i]);
        }
    }
    //对Node进行计算分数
    public void calculateScore(Pod pod){
        //遍历每个节点，进行打分，打分标准是那四个优化的公式
        for(int i=0;i<5;i++){
            double score1 = getScore1(nodeList.get(i),pod);
            double score2 = getScore2(nodeList.get(i),pod);
            double score3 = getScore3(nodeList.get(i),pod);
            double score4 = getScore4(nodeList.get(i),0.4, 1, 0.95);
            //如果超出水位，则不能进行调度，设置分数为0;
            if(score4==0) {
                nodeList.get(i).setScore(0);
            }
            else {
                System.out.println(score3);
                double score = getTotalScore(score1,score2,score3,score4);
                nodeList.get(i).setScore(score);
            }
        }
    }
    //资源利用率计算公式
    public static double getScore1(Node node,Pod pod){
        double score_cpu=0,score_mem=0;
        if(node.getCPU_total()!=0){
            score_cpu = (double) (node.getCPU_total()-node.getCPU_used()-(pod.getCPU_L()+pod.getCPU_R())/2)/node.getCPU_total();
        }
        if(node.getMEM_total()!=0){
            score_mem = (double) (node.getMEM_total()-node.getMEM_used()-(pod.getMEM_L()+pod.getMEM_R())/2)/node.getMEM_total();
        }
        return (score_cpu*10+score_mem*10)/2;
    }
    //资源均衡度计算公式
    public double getScore2(Node node,Pod pod){
        double score_cpu = (double) (node.getCPU_used()+(pod.getCPU_L()+pod.getCPU_R())/2)/node.getCPU_total();
        double score_mem = (double) (node.getMEM_used()+(pod.getMEM_L()+pod.getMEM_R())/2)/node.getMEM_total();
        return (1-Math.abs(score_cpu-score_mem))*10;
    }
    //划分队列计算公式
    public double getScore3(Node node,Pod pod){
        double left = 0;
        double right = 0;
        //初始状态得分都是0，无法计算啊。。。
//        if(pod.getQueue()==1){
//            left = 1-(double)Math.abs((node.getQueue_one()+1-node.getQueue_four())/(node.getQueue_one()+node.getQueue_four()+1));
//            if(node.getQueue_two()+node.getQueue_three()!=0){
//                right = 1-(double)Math.abs((node.getQueue_two()-node.getQueue_three())/(node.getQueue_two()+node.getQueue_three()));
//            }
//        }
//        if(pod.getQueue()==2){
//            if(node.getQueue_one()+node.getQueue_four()!=0 ){
//                left = 1-(double)Math.abs((node.getQueue_one()-node.getQueue_four())/(node.getQueue_one()+node.getQueue_four()));
//            }
//            right = 1-(double)Math.abs((node.getQueue_two()+1-node.getQueue_three())/(node.getQueue_two()+node.getQueue_three()+1));
//        }
//        if(pod.getQueue()==3){
//            if(node.getQueue_one()+node.getQueue_four()!=0 ){
//                left = 1-(double)Math.abs((node.getQueue_one()-node.getQueue_four())/(node.getQueue_one()+node.getQueue_four()));
//            }
//            right = 1-(double)Math.abs((node.getQueue_two()-node.getQueue_three()-1)/(node.getQueue_two()+node.getQueue_three()+1));
//        }
//        if(pod.getQueue()==4){
//            left = 1-(double)Math.abs((node.getQueue_one()-node.getQueue_four()-1)/(node.getQueue_one()+node.getQueue_four()+1));
//            if(node.getQueue_two()+node.getQueue_three()!=0){
//                right = 1-(double)Math.abs((node.getQueue_two()-node.getQueue_three())/(node.getQueue_two()+node.getQueue_three()));
//            }
//        }
        if(pod.getQueue()==1 || pod.getQueue()==4){
            if(node.getQueue_one()+node.getQueue_four()+1>(node.getQueue_two()+node.getQueue_three())){
                left = (double)(node.getQueue_two()+node.getQueue_three())/(node.getQueue_one()+node.getQueue_four()+1);
            }
            else {
                left = (double)(node.getQueue_one()+node.getQueue_four()+1)/(node.getQueue_two()+node.getQueue_three());
            }
        }
        if(pod.getQueue()==2 || pod.getQueue()==3){
            if(node.getQueue_one()+node.getQueue_four()>(node.getQueue_two()+node.getQueue_three()+1)){
                left = (double)(node.getQueue_two()+node.getQueue_three()+1)/(node.getQueue_one()+node.getQueue_four());
            }
            else {
                left = (double)(node.getQueue_one()+node.getQueue_four())/(node.getQueue_two()+node.getQueue_three()+1);
            }
        }
        return left*10;
    }
    //阈值水位计算公式
    public double getScore4(Node node, double E1, double K, double E2){
        double score4 = 0;
        //U为节点负载，CPU和内存负载的平均值
        double U = ((double)node.getCPU_used()/node.getCPU_total()+(double)node.getMEM_used()/node.getMEM_total())/2;
        if(U<=E1){
             score4 = (((1-E1)/E1)*U+E1)*10;
        }
        else if(U>E1 && U<=E2){
             score4 = (K*(1-U)/(1-E1))*10;
        }
        else if(U>=E2){
            score4 = 0;
        }
        return score4;
    }
    public double getTotalScore(double score1,double score2,double score3,double score4){
        //可以随机改变系数值来求得总分数
        return k*score1+p*score2+m*score3+n*score4;
    }
    //选择得分最高的节点
    public Node getMaxScoreNode(Pod pod){
        double max_score = 0;
        int perfect_index = 0;
        for(int i=0;i<5;i++){
            if((nodeList.get(i).getCPU_total()- nodeList.get(i).getCPU_used())<(pod.getCPU_R()+pod.getCPU_L())/2 || (nodeList.get(i).getMEM_total()- nodeList.get(i).getMEM_used())<(pod.getMEM_R()+pod.getMEM_L())/2){
                continue;
            }
            else if(nodeList.get(i).getScore()>max_score){
                max_score = nodeList.get(i).getScore();
                perfect_index = i;
            }
        }
        if(max_score==0) return null;
        return nodeList.get(perfect_index);
    }
    //对得分最高的节点绑定到来的一个Pod,修改队列值，修改已用CPU和内存
    public void bindPodToPerfectNode(Pod pod,Node perfectNode){
        int queue = pod.getQueue();
        if(queue==1){
            int queue_one = perfectNode.getQueue_one();
            queue_one++;
            perfectNode.setQueue_one(queue_one);
        }
        if(queue==2){
            int queue_two = perfectNode.getQueue_two();
            queue_two++;
            perfectNode.setQueue_two(queue_two);
        }
        if(queue==3){
            int queue_three = perfectNode.getQueue_three();
            queue_three++;
            perfectNode.setQueue_three(queue_three);
        }
        if(queue==4){
            int queue_four = perfectNode.getQueue_four();
            queue_four++;
            perfectNode.setQueue_four(queue_four);
        }
        //修改已用的CPU和MEM
        int CPU_used = perfectNode.getCPU_used();
        CPU_used += pod.getCPU_avg();
        perfectNode.setCPU_used(CPU_used);
        int MEM_used = perfectNode.getMEM_used();
        MEM_used += pod.getMEM_avg();
        perfectNode.setMEM_used(MEM_used);
    }
    //判断Node的CPU和内存是否已经超过阈值
    public boolean isExceedThreshold(Node node,double HighThreshold){
        if (node.getCPU_total()!=0 && node.getMEM_total()!=0){
            if(node.getCPU_used()/node.getCPU_total()>=HighThreshold){
                return false;
            }
            if(node.getMEM_used()/node.getMEM_total()>=HighThreshold){
                return false;
            }
        }
        return true;
    }
    //pod调度失败后，重新插入队列,写在主过程里


    //对pod进行打分，便于后面建立优先级队列
    public void initPodScore(int n){
        //根据时间和依赖资源稳定性（即limit和request的方差）
        //计算每个pod的分值
        for(int i=0;i<n;i++){

        }
    }

    public void printResult(int node_num){
        //打印结果，主要打印出每个节点的剩余资源量、调度的失败个数、最终的分配方案（打印每个节点的各队列Pod个数）
        for(int i=0;i<node_num;i++){
            System.out.println("the node id:"+i+" , and the CPU remain:"+(nodeList.get(i).getCPU_total()-nodeList.get(i).getCPU_used())+ ",the MEM remain: "+(nodeList.get(i).getMEM_total()-nodeList.get(i).getMEM_used())+" the rate :"+getUseRate(nodeList.get(i))+" "+"queue one:"+nodeList.get(i).getQueue_one()+" queue two:"+nodeList.get(i).getQueue_two()+" queue three:"+nodeList.get(i).getQueue_three()+" queue four:"+nodeList.get(i).getQueue_four());
//            System.out.println("the queue one pod: "+ nodeList.get(i).getQueue_one()+" the queue two pod: "+ nodeList.get(i).getQueue_two()+" the queue three pod: "+ nodeList.get(i).getQueue_three()+" the queue four pod: "+ nodeList.get(i).getQueue_four());
            System.out.println("---------------------------------------------");
        }
        double sum = 0;
        double avg = 0;
        double res = 0;
        for(int i=0;i<node_num;i++){
            sum += getUseRate(nodeList.get(i));
        }
        avg = sum/(double) node_num;
        for(int j=0;j<node_num;j++){
            res +=Math.pow((getUseRate(nodeList.get(j))-avg),2);
        }
        System.out.println("----------------------------------------------");
        System.out.print(res/(double) node_num);
    }
    public double getUseRate(Node node){
        double res = (double) node.getCPU_used()/(double) node.getCPU_total()+(double) node.getMEM_used()/(double) node.getMEM_total();
        return res/2;
    }
    //默认的调度算法1的调度
    public Node getMaxScore1Node(Pod pod,List<Node> nodeList){
        double maxScore = 0;
        double score = 0;
        int index = 0;
        for(int i = 0; i< nodeList.size(); i++){
            if((nodeList.get(i).getCPU_total()- nodeList.get(i).getCPU_used())<(pod.getCPU_R()+pod.getCPU_L())/2 || (nodeList.get(i).getMEM_total()- nodeList.get(i).getMEM_used())<(pod.getMEM_R()+pod.getMEM_L())/2){
                continue;
            }
            else {
                score = getScore1(nodeList.get(i),pod);
                if(score>maxScore){
                    index = i;
                    maxScore = score;
                }
            }
        }
        if(maxScore==0) return null;
        else return nodeList.get(index);
    }
    //调度算法1把最优的节点绑定Pod
    public void bindPodToNode1(Node perfectNode,Pod pod){
        //修改已用的CPU和MEM
        int CPU_used = perfectNode.getCPU_used();
        CPU_used += pod.getCPU_avg();
        perfectNode.setCPU_used(CPU_used);
        int MEM_used = perfectNode.getMEM_used();
        MEM_used += pod.getMEM_avg();
        perfectNode.setMEM_used(MEM_used);
    }

    //默认调度算法2
    public Node getMaxScore2Node(Pod pod,List<Node> nodeList){
        double maxScore2 = 0;
        double score = 0;
        int index = -1;
        for(int i=0;i<nodeList.size();i++){
            if((nodeList.get(i).getCPU_total()- nodeList.get(i).getCPU_used())>=(pod.getCPU_R()+pod.getCPU_L())/2 && (nodeList.get(i).getMEM_total()- nodeList.get(i).getMEM_used())>=(pod.getMEM_R()+pod.getMEM_L())/2){
                score = (getScore1(nodeList.get(i),pod)+getScore2(nodeList.get(i),pod))/2;
                if(score>maxScore2){
                    index = i;
                    maxScore2 = score;
                }
            }
        }
        if(maxScore2==0) return null;
        else return nodeList.get(index);
    }
    public void bindPodToNode2(Node perfectNode,Pod pod){
        int CPU_used = perfectNode.getCPU_used();
        CPU_used += pod.getCPU_avg();
        perfectNode.setCPU_used(CPU_used);
        int MEM_used = perfectNode.getMEM_used();
        MEM_used += pod.getMEM_avg();
        perfectNode.setMEM_used(MEM_used);
    }

}
