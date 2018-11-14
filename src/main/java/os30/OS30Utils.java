package os30;

import org.junit.jupiter.api.Test;

import java.io.*;

public class OS30Utils {

    public static void copyFiles(String src,String desDir) throws IOException {

        File file=new File(src);
        if (file.isFile()){
            copyOneFile(file,desDir);
        }else{
            File[] files=file.listFiles();
            for (File f:files) {
                if(file.getName().indexOf("_day")!=-1||file.getName().indexOf("projects")!=-1)
                    copyFiles(f.getAbsolutePath(), desDir);
                else
                    copyFiles(f.getAbsolutePath(), desDir + "\\" + file.getName());
            }
        }
    }


    public static void copyOneFile(File src,String desDir) throws IOException {
        byte[] byte_arr=new byte[1024];
        int len=0;
        FileInputStream fis=new FileInputStream(src);
        File desFile=new File(desDir);
        if (!desFile.exists())
            desFile.mkdirs();
        FileOutputStream fos=new FileOutputStream(desDir+"\\"+src.getName());
        while ((len=fis.read(byte_arr,0,byte_arr.length))!=-1){
            fos.write(byte_arr,0,len);
        }
        fis.close();
        fos.close();
    }

    public void insertRunFile(File src,String desDir) throws IOException {
        byte[] byte_arr=new byte[1024];
        int len;
        File desFile=new File(desDir);
        if (desFile.isDirectory()) {
            File[] files = desFile.listFiles();
            for (File f:files){
                if (f.isDirectory()){
                    FileInputStream fis=new FileInputStream(src);
                    File file=new File(desDir+"\\"+f.getName()+"\\"+src.getName());
                    if(file.exists())
                        continue;
                    FileOutputStream fos=new FileOutputStream(file);
                    while ((len=fis.read(byte_arr,0,byte_arr.length))!=-1){
                        fos.write(byte_arr,0,len);
                    }
                    fis.close();
                    fos.close();
                }

            }
        }

    }

    @Test
    public void testCopy() throws IOException {
        //copyFiles("C:\\Users\\siege\\Desktop\\30os\\projects","D:\\des");
        copyFiles("C:\\Users\\siege\\Desktop\\30os\\projects","C:\\Users\\siege\\Desktop\\30os\\tolset");

    }

    @Test
    public void testInsert() throws IOException {
        //insertRunFile(new File("C:\\Users\\siege\\Desktop\\30os\\tolset\\harib01g\\run.bat"),"D:\\des");
        //insertRunFile(new File("C:\\Users\\siege\\Desktop\\30os\\tolset\\harib01g\\run.bat"),"C:\\Users\\siege\\Desktop\\30os\\tolset");
        insertRunFile(new File("F:\\booktool\\source\\c05\\run.bat"),"F:\\booktool\\source");
    }

    @Test
    public void testAll() throws IOException {
        testCopy();
        testInsert();
    }

    @Test
    public void testCmd() throws IOException, InterruptedException {
        Runtime runtime=Runtime.getRuntime();
        Process process=runtime.exec("cmd.exe /c  run.bat",null,new File("C:\\Users\\siege\\Desktop\\30os\\tolset\\harib01g"));
        process.waitFor();
        //runtime.exec("cmd.exe /c  start   cd C:\\Users\\siege\\Desktop\\30os\\tolset\\harib01g");
        //runtime.exec("cmd.exe /c  start  C:\\Users\\siege\\Desktop\\30os\\tolset\\harib01g\\run.bat");
    }

    public static void makeFile(String path) throws IOException, InterruptedException {
        Runtime runtime=Runtime.getRuntime();
        File file =new File(path);
        if(file.isDirectory()){
            File[] files=file.listFiles();
            for (File f:files){
                if(f.isDirectory()){
                    Process process=runtime.exec("cmd.exe /c start   make.bat",null,new File(f.getAbsolutePath()));
                    //process.waitFor();
                    break;
                }
            }
        }
    }

    @Test
    public  void testMakeFile() throws IOException, InterruptedException {
        makeFile("C:\\Users\\siege\\Desktop\\30os\\tolset");
    }
}
