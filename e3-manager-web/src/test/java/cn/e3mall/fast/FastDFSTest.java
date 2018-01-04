package cn.e3mall.fast;

/**
 * FastDFSTest
 *
 * @author guxiang
 * @date 2017/12/24
 */

import com.guxiang.common.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;
import sun.net.www.content.image.png;

import java.awt.*;

public class FastDFSTest {
    @Test
    public void testUpload() throws Exception {
        //创建一个配置文件。文件名任意。内容就是tracker服务器的地址。
        //使用全局对象加载配置文件。
        ClientGlobal.init("D:\\IntellijIDEAWorkSpace\\e3mailproject\\e3managerweb\\src\\main\\resources\\conf\\client.conf");
        //创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackClient获得一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //创建一个StrorageServer的引用，可以是null
        StorageServer storageServer = null;
        //创建一个StorageClient，参数需要TrackerServer和StrorageServer
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //使用StorageClient上传文件。
        String[] strings = storageClient.upload_file("C:\\Users\\guxiang\\Desktop\\文档\\桌面背景\\桌面背景\\1.png ", "png", null);
        for (String string : strings) {
            System.out.println(string);
        }


    }

    @Test
    public void testFastDfsClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("D:\\IntellijIDEAWorkSpace\\e3mailproject\\e3managerweb\\src\\main\\resources\\conf\\client.conf");
        String string = fastDFSClient.uploadFile("C:\\Users\\guxiang\\Desktop\\文档\\桌面背景\\桌面背景\\33.jpg");
        System.out.println(string);
    }
}
