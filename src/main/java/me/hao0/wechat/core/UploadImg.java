package me.hao0.wechat.core;

import java.io.InputStream;
import java.util.Map;

import static me.hao0.common.util.Preconditions.checkNotNull;
import static me.hao0.common.util.Preconditions.checkNotNullAndEmpty;

/**
 * 上传图片接口
 *
 * @author zJun
 * @date 2017年12月27日 上午10:34:14
 */
public class UploadImg extends Component {

    /**
     * 上传图片接口
     * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025056
     */
    private static final String UPLOAD_LOGO = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=";


    /**
     * 上传图片 LOGO
     *
     * @param accessToken accessToken
     * @param fileName    文件名
     * @param input       文件输入流
     * @return 上传成功返回图片链接
     * @author zJun
     * @date 2017年12月8日 下午3:54:21
     */
    public String uploadHead(String accessToken, String fileName, InputStream input) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        checkNotNullAndEmpty(fileName, "fileName");
        checkNotNull(input, "上传文件不能为空。");
        String url = UPLOAD_LOGO + accessToken;
        Map<String, Object> result = doUpload(url, "media", fileName, input);
        return result.get("url").toString();
    }

    /**
     * 上传图片 LOGO
     *
     * @param fileName
     * @param input
     * @return
     * @author zJun
     * @date 2017年12月28日 上午10:40:43
     */
    public String uploadHead(String fileName, InputStream input) {
        return uploadHead(loadAccessToken(), fileName, input);
    }
}
