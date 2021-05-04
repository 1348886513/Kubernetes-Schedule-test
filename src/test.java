public class test {
    public static void main(String[] args) {
        String str = "10 17 39 31 24 33 16 30 11 32 21 20 23 20 37 34 13 22 29 38 14 14 10 29 13 10 31 12 38 35 39 29 21 31 30 27 22 26 23 39 19 17 26 31 20 34 23 21 33 10";
        String[] s = str.split(" ");
        for(int i=0;i<s.length;i++){
            System.out.print(s[i]+",");
        }
    }
}
