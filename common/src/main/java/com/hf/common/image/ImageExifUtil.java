package com.hf.common.image;

import com.alibaba.fastjson.JSON;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * EXIF是 Exchangeable Image File的缩写， 这是一种专门为数码相机照片设定的格式。
 * <p>
 * 这种格式可以用来记录数字照片的属性信息，
 * 例如相机的品牌及型号、相片的拍摄时间、拍摄时所设置的光圈大小、快门速度、ISO等等信息。
 * 除此之外它还能够记录拍摄数据，以及照片格式化方式
 * <p>
 * 目前最简单易用的EXIF信息处理的Java包是Drew Noakes写的metadata-extractor
 * <p>
 * Created by HF on 2017/9/17.
 */
public class ImageExifUtil {

    private static Logger logger;
    static {
        logger = LoggerFactory.getLogger(ImageExifUtil.class);
    }

    public static void main(String[] args) {
        File file = new File("E:\\myImg\\旅游\\稻城亚丁\\IMG_20170719_111419.jpg");
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            Map<String,Object> map = printAllMeta(metadata);
            System.out.println(JSON.toJSONString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 打印所有元数据
     * idea 查看以下信息{@code Ctrl + q}
     * <table border="1" cellspacing="0" cellpadding="0" width="100%">
     * <tbody>
     * <tr><td valign="top"><p align="center"><strong>代码</strong></p></td>
     * <td valign="top"><p align="center"><strong>英文解释</strong></p></td>
     * <td valign="top"><p align="center"><strong>中文解释</strong></p></td></tr>
     * <tr><td valign="top"><p align="left">0x0000</p></td>
     * <td valign="top"><p align="left">GPS Version ID</p></td>
     * <td valign="top"><p align="left">GPS版本</p></td></tr>
     * <tr><td valign="top"><p align="left">0x010f</p></td>
     * <td valign="top"><p align="left">Make</p></td>
     * <td valign="top"><p align="left">厂商</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0110</p></td>
     * <td valign="top"><p align="left">Model</p></td>
     * <td valign="top"><p align="left">机型</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0112</p></td>
     * <td valign="top"><p align="left">Orientation</p></td>
     * <td valign="top"><p align="left">方向</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0131</p></td>
     * <td valign="top"><p align="left">Software</p></td>
     * <td valign="top"><p align="left">软件</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0132</p></td>
     * <td valign="top"><p align="left">Date/Time</p></td>
     * <td valign="top"><p align="left">修改时间</p></td></tr>
     * <tr><td valign="top"><p align="left">0x013b</p></td>
     * <td valign="top"><p align="left">Artist</p></td>
     * <td valign="top"><p align="left">作者</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0213</p></td>
     * <td valign="top"><p align="left">YCbCr Positioning</p></td>
     * <td valign="top"><p align="left">YcbCr定位</p></td></tr>
     * <tr><td valign="top"><p align="left">0x829a</p></td>
     * <td valign="top"><p align="left">Exposure Time</p></td>
     * <td valign="top"><p align="left">曝光时间</p></td></tr>
     * <tr><td valign="top"><p align="left">0x829d</p></td>
     * <td valign="top"><p align="left">F-Number</p></td>
     * <td valign="top"><p align="left">光圈</p></td></tr>
     * <tr><td valign="top"><p align="left">0x8822</p></td>
     * <td valign="top"><p align="left">Exposure Program</p></td>
     * <td valign="top"><p align="left">曝光程序</p></td></tr>
     * <tr><td valign="top"><p align="left">0x8827</p></td>
     * <td valign="top"><p align="left">ISO Speed Ratings</p></td>
     * <td valign="top"><p align="left">ISO感光度</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9000</p></td>
     * <td valign="top"><p align="left">Exif Version</p></td>
     * <td valign="top"><p align="left">Exif版本</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9003</p></td>
     * <td valign="top"><p align="left">Date/Time Original</p></td>
     * <td valign="top"><p align="left">拍摄时间</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9004</p></td>
     * <td valign="top"><p align="left">Date/Time Digitized</p></td>
     * <td valign="top"><p align="left">数字化时间</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9101</p></td>
     * <td valign="top"><p align="left">Components Configuration</p></td>
     * <td valign="top"><p align="left">成分构成</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9201</p></td>
     * <td valign="top"><p align="left">Shutter Speed Value</p></td>
     * <td valign="top"><p align="left">快门速度</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9202</p></td>
     * <td valign="top"><p align="left">Aperture Value</p></td>
     * <td valign="top"><p align="left">光圈值</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9204</p></td>
     * <td valign="top"><p align="left">Exposure Bias Value</p></td>
     * <td valign="top"><p align="left">曝光补偿</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9205</p></td>
     * <td valign="top"><p align="left">Max Aperture Value</p></td>
     * <td valign="top"><p align="left">最大光圈</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9207</p></td>
     * <td valign="top"><p align="left">Metering Mode</p></td>
     * <td valign="top"><p align="left">测光模式</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9209</p></td>
     * <td valign="top"><p align="left">Flash</p></td>
     * <td valign="top"><p align="left">闪光</p></td></tr>
     * <tr><td valign="top"><p align="left">0x920a</p></td>
     * <td valign="top"><p align="left">Focal Length</p></td>
     * <td valign="top"><p align="left">焦距</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9286</p></td>
     * <td valign="top"><p align="left">User Comment</p></td>
     * <td valign="top"><p align="left">用户注释</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9290</p></td>
     * <td valign="top"><p align="left">Sub-Sec Time</p></td>
     * <td valign="top"><p align="left">次秒（修改时间）</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9291</p></td>
     * <td valign="top"><p align="left">Sub-Sec Time Original</p></td>
     * <td valign="top"><p align="left">次秒（拍摄时间）</p></td></tr>
     * <tr><td valign="top"><p align="left">0x9292</p></td>
     * <td valign="top"><p align="left">Sub-Sec Time Digitized</p></td>
     * <td valign="top"><p align="left">次秒（数字化时间）</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa000</p></td>
     * <td valign="top"><p align="left">FlashPix Version</p></td>
     * <td valign="top"><p align="left">FlashPix版本</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa001</p></td>
     * <td valign="top"><p align="left">Color Space</p></td>
     * <td valign="top"><p align="left">色彩空间</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa002</p></td>
     * <td valign="top"><p align="left">Exif Image Width</p></td>
     * <td valign="top"><p align="left">Exif图像宽度</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa003</p></td>
     * <td valign="top"><p align="left">Exif Image Height</p></td>
     * <td valign="top"><p align="left">Exif图像高度</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa20e</p></td>
     * <td valign="top"><p align="left">Focal Plane X Resolution</p></td>
     * <td valign="top"><p align="left">焦平面水平分辨率</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa20f</p></td>
     * <td valign="top"><p align="left">Focal Plane Y Resolution</p></td>
     * <td valign="top"><p align="left">焦平面垂直分辨率</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa210</p></td>
     * <td valign="top"><p align="left">Focal Plane Resolution Unit</p></td>
     * <td valign="top"><p align="left">焦平分辨率单位</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa401</p></td>
     * <td valign="top"><p align="left">Custom Rendered</p></td>
     * <td valign="top"><p align="left">自定义补偿</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa402</p></td>
     * <td valign="top"><p align="left">Exposure Mode</p></td>
     * <td valign="top"><p align="left">曝光模式</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa403</p></td>
     * <td valign="top"><p align="left">White Balance Mode</p></td>
     * <td valign="top"><p align="left">白平衡</p></td></tr>
     * <tr><td valign="top"><p align="left">0xa406</p></td>
     * <td valign="top"><p align="left">Scene Capture Type</p></td>
     * <td valign="top"><p align="left">场景拍摄类型</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0001</p></td>
     * <td valign="top"><p align="left">Interoperability Index</p></td>
     * <td valign="top"><p align="left">可交换标准</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0002</p></td>
     * <td valign="top"><p align="left">Interoperability Version</p></td>
     * <td valign="top"><p align="left">可交换版本</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0103</p></td>
     * <td valign="top"><p align="left">Thumbnail Compression</p></td>
     * <td valign="top"><p align="left">压缩模式</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0201</p></td>
     * <td valign="top"><p align="left">Thumbnail Offset</p></td>
     * <td valign="top"><p align="left">JPEG缩略图起始位置</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0202</p></td>
     * <td valign="top"><p align="left">Thumbnail Length</p></td>
     * <td valign="top"><p align="left">JPEG缩略图数据长度</p></td></tr>
     * <tr><td valign="top"><p align="left">0x011a</p></td>
     * <td valign="top"><p align="left">X Resolution</p></td>
     * <td valign="top"><p align="left">水平分辨率</p></td></tr>
     * <tr><td valign="top"><p align="left">0x011b</p></td>
     * <td valign="top"><p align="left">Y Resolution</p></td>
     * <td valign="top"><p align="left">垂直分辨率</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0128</p></td>
     * <td valign="top"><p align="left">Resolution Unit</p></td>
     * <td valign="top"><p align="left">分辨率单位</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0131</p></td>
     * <td valign="top"><p align="left">Software</p></td>
     * <td valign="top"><p align="left">软件</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0006</p></td>
     * <td valign="top"><p align="left">Lens Information</p></td>
     * <td valign="top"><p align="left">镜头信息</p></td></tr>
     * <tr><td valign="top"><p align="left">0x0007</p></td>
     * <td valign="top"><p align="left">Lens</p></td>
     * <td valign="top"><p align="left">镜头</p></td></tr>
     * </tbody>
     * </table>
     * @param metadata
     */
    private static Map<String,Object> printAllMeta(Metadata metadata) {
        Map<String,Object> map = new HashMap<>();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                map.put(tag.getTagName(),tag.getDescription());
                // System.out.print(tag.getTagName() + " --> ");
                // System.out.println(tag.getDescription());
            }

            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    logger.error("ERROR: %s",error);
                }
            }
        }
        return map;
    }

}
