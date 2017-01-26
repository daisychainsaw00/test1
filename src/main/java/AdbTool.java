import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AdbTool {

    //master!!!233333
    public static void main(String[] args) {
        //String aaa = getDeviceId(1);
        //System.out.println("设备id是: "+getDeviceId(1));
        //System.out.println(aaa);
        //System.out.println("设备版本号是: "+getAndroidVersion(1));
        System.out.println("当前: "+System.getProperty("user.name"));
    }


    public static Process cmd(String command) throws IOException {
        //System.out.println("调用cmd");
        return Runtime.getRuntime().exec(command);
    }

    /**
     *
     * @param index 第几个设备,从1
     * @return 设备id
     */
    public static String getDeviceId(int index){

        Process process= null;
        try {
            process = Runtime.getRuntime().exec("adb devices");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        InputStreamReader isr=new InputStreamReader(process.getInputStream());

        Scanner sc=new Scanner(isr);
        ArrayList<String> dinfo = new ArrayList<String>();
        while(sc.hasNext()){
            //System.out.println(sc.next());
            dinfo.add(sc.next());
        }
        int i = index*2+2;
        String deviceid = null;
        try {
            deviceid = dinfo.get(i);
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println("no way,non so much, so take the 1st");
            deviceid = dinfo.get(4);

        }
        //System.out.println(deviceid);
        //System.out.println(dinfo);
        return deviceid;
    }

    public static String getAndroidVersion(int index){
        Process process= null;
        String cmd = "adb -s "+getDeviceId(index)+" shell getprop ro.build.version.release";
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        InputStreamReader isr=new InputStreamReader(process.getInputStream());

        Scanner sc=new Scanner(isr);
        String version=null;
        //ArrayList<String> dinfo = new ArrayList<String>();
        while(sc.hasNext()){
            //System.out.println(sc.next());
            version = sc.next();
        }

        return version;

    }
}