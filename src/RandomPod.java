import java.util.*;

public class RandomPod {
    public static void main(String[] args) {
        int[] R_CPU = getRequestCPU(50);
        System.out.print("Request CPU: ");
        for(int i=0;i<50;i++){
            System.out.print(R_CPU[i]+",");
        }
        System.out.println();
        System.out.print("Limit CPU: ");
        //通过R_CPU计算L_CPU
        Random r = new Random();
        int[] L_CPU = new int[50];
        for(int i=0;i<50;i++){
            L_CPU[i] = r.nextInt(20)+R_CPU[i];
            System.out.print(L_CPU[i]+",");
        }
        System.out.println();
        System.out.print("the avg CPU: ");
        for(int i=0;i<50;i++){
            System.out.print((L_CPU[i]+R_CPU[i])/2+",");
        }
        System.out.println();
        System.out.print("Request MEM: ");
        int[] R_MEM = getRequestMEM(50);
        for(int i=0;i<50;i++){
            System.out.print(R_MEM[i]+",");
        }
        System.out.println();
        System.out.print("Limit MEM: ");
        int[] L_MEM = new int[50];
        for(int i=0;i<50;i++){
            L_MEM[i] = r.nextInt(300)+R_MEM[i];
            System.out.print(L_MEM[i]+",");
        }
        System.out.println();
        System.out.print("The avg MEM: ");
        for(int i=0;i<50;i++){
            System.out.print((L_MEM[i]+R_MEM[i])/2+",");
        }
    }
    public static int[] getLimitCPU(int n){
        int[] L_CPU = new int[n];
        Random r = new Random();
        for(int i=0;i<n;i++){
            L_CPU[i] = r.nextInt(30)+10;
        }
        return L_CPU;
    }
    public static int[] getRequestCPU(int n){
        int[] R_CPU = new int[n];
        Random r = new Random();
        for(int i=0;i<n;i++){
            R_CPU[i] = r.nextInt(40)+10;
        }
        return R_CPU;
    }
    public static int[] getLimitMEM(int n){
        int[] L_MEM = new int[n];
        Random r = new Random();
        for(int i=0;i<n;i++){
            L_MEM[i] = r.nextInt(350)+300;
        }
        return L_MEM;
    }
    public static int[] getRequestMEM(int n){
        int[] R_MEM = new int[n];
        Random r = new Random();
        for(int i=0;i<n;i++){
            R_MEM[i] = r.nextInt(500)+250;
        }
        return R_MEM;
    }
}
