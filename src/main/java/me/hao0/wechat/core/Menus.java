package me.hao0.wechat.core;

import com.fasterxml.jackson.databind.JavaType;
import me.hao0.common.json.Jsons;
import me.hao0.wechat.exception.WechatException;
import me.hao0.wechat.model.menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static me.hao0.common.util.Preconditions.checkNotNullAndEmpty;

/**
 * 菜单组件
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 18/11/15
 *
 * @since 1.4.0
 */
public final class Menus extends Component {

    /**
     * 查询菜单
     */
    private static final String GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";

    /**
     * 创建菜单
     */
    private static final String CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    /**
     * 删除菜单
     */
    private static final String DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
    
    /** 创建个性化菜单 */
    private static final String ADDCONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=";
    
    /** 删除个性化菜单 */
    private static final String DELCONDITIONAL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=";
    
    /** 测试个性化菜单匹配结果 */
    private static final String TRYMATCH = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=";

    private static final JavaType ARRAY_LIST_MENU_TYPE = Jsons.DEFAULT.createCollectionType(ArrayList.class, Menu.class);

    Menus() {
    }

    /**
     * 查询菜单
     *
     * @return 菜单列表
     */
    public List<Menu> get() {
        return get(loadAccessToken());
    }

    /**
     * 查询菜单
     *
     * @param cb 回调
     */
    public void get(Callback<List<Menu>> cb) {
        get(loadAccessToken(), cb);
    }

    /**
     * 查询菜单
     *
     * @param accessToken accessToken
     * @param cb          回调
     */
    public void get(final String accessToken, Callback<List<Menu>> cb) {
        doAsync(new AsyncFunction<List<Menu>>(cb) {
            @Override
            public List<Menu> execute() {
                return get(accessToken);
            }
        });
    }

    /**
     * 查询菜单
     *
     * @param accessToken accessToken
     * @return 菜单列表
     */
    public List<Menu> get(String accessToken) {
        checkNotNullAndEmpty(accessToken, "accessToken");

        String url = GET + accessToken;
        Map<String, Object> resp = doGet(url);
        String jsonMenu = Jsons.DEFAULT.toJson(((Map<?, ?>) resp.get("menu")).get("button"));
        return Jsons.EXCLUDE_DEFAULT.fromJson(jsonMenu, ARRAY_LIST_MENU_TYPE);
    }

    /**
     * 创建菜单
     *
     * @param jsonMenu 菜单json
     * @return 创建成功返回true，或抛WechatException
     */
    public Boolean create(String jsonMenu) {
        return create(loadAccessToken(), jsonMenu);
    }

    /**
     * 创建菜单
     *
     * @param accessToken 访问token
     * @param jsonMenu    菜单json
     * @param cb          回调
     */
    public void create(final String accessToken, final String jsonMenu, Callback<Boolean> cb) {
        doAsync(new AsyncFunction<Boolean>(cb) {
            @Override
            public Boolean execute() {
                return create(accessToken, jsonMenu);
            }
        });
    }

    /**
     * 创建菜单
     *
     * @param jsonMenu 菜单json
     * @param cb       回调
     */
    public void create(final String jsonMenu, Callback<Boolean> cb) {
        create(loadAccessToken(), jsonMenu, cb);
    }

    /**
     * 创建菜单
     *
     * @param accessToken 访问token
     * @param jsonMenu    菜单json
     * @return 创建成功返回true，或抛WechatException
     */
    public Boolean create(String accessToken, String jsonMenu) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        checkNotNullAndEmpty(jsonMenu, "jsonMenu");

        String url = CREATE + accessToken;
        doPost(url, jsonMenu);
        return Boolean.TRUE;
    }

    /**
     * 删除菜单
     *
     * @return 删除成功返回true，或抛WechatException
     */
    public Boolean delete() {
        return delete(loadAccessToken());
    }

    /**
     * 删除菜单
     *
     * @param cb 回调
     */
    public void delete(Callback<Boolean> cb) {
        delete(loadAccessToken(), cb);
    }

    /**
     * 删除菜单
     *
     * @param accessToken accessToken
     * @param cb          回调
     */
    public void delete(final String accessToken, Callback<Boolean> cb) {
        doAsync(new AsyncFunction<Boolean>(cb) {
            @Override
            public Boolean execute() {
                return delete(accessToken);
            }
        });
    }

    /**
     * 删除菜单
     *
     * @param accessToken accessToken
     * @return 删除成功返回true，或抛WechatException
     */
    public Boolean delete(String accessToken) {
        checkNotNullAndEmpty(accessToken, "accessToken");

        String url = DELETE + accessToken;
        doGet(url);
        return Boolean.TRUE;
    }
    
    /**
     * 创建个性化菜单
     * @param jsonMenu
     * @return
     * @author zJun
     * @date 2018年3月16日 下午6:07:38
     */
    public String createGX(String jsonMenu) {
        return createGX(loadAccessToken(), jsonMenu);
    }
    
    /**
     * 创建个性化菜单
     * @param accessToken
     * @param jsonMenu
     * @return
     * @author zJun
     * @date 2018年3月16日 下午6:11:00
     */
    public String createGX(String accessToken, String jsonMenu) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        checkNotNullAndEmpty(jsonMenu, "jsonMenu");

        String url = ADDCONDITIONAL + accessToken;
        Map<String, Object> result = doPost(url, jsonMenu);
        Object menuid = result.get("menuid");
        if(menuid == null) {
            throw new WechatException("创建个性化菜单失败");
        }
        return menuid.toString();
    }
    
    /**
     * 删除个性化菜单
     * @param accessToken
     * @param jsonMenu
     * @author zJun
     * @date 2018年3月19日 下午2:17:03
     */
    public Boolean deleteGX(String accessToken, String jsonMenu) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        checkNotNullAndEmpty(jsonMenu, "jsonMenu");
        
        String url = DELCONDITIONAL + accessToken;
        doPost(url, jsonMenu);
        return Boolean.TRUE;
    }
    
    /**
     * 删除个性化菜单
     * @param accessToken
     * @param jsonMenu
     * @author zJun
     * @date 2018年3月19日 下午2:17:03
     */
    public Boolean deleteGX(String jsonMenu) {
        return deleteGX(loadAccessToken(), jsonMenu);
    }
    
    /**
     * 测试匹配菜单
     * @param accessToken
     * @param jsonMenu
     * @return
     * @author zJun
     * @date 2018年3月19日 下午2:25:17
     */
    public Map<String, Object> trymatch(String accessToken, String jsonMenu) {
        checkNotNullAndEmpty(accessToken, "accessToken");
        checkNotNullAndEmpty(jsonMenu, "jsonMenu");
        
        String url = TRYMATCH + accessToken;
        return doPost(url, jsonMenu);
    }
    
    /**
     * 测试匹配菜单
     * @param accessToken
     * @param jsonMenu
     * @return
     * @author zJun
     * @date 2018年3月19日 下午2:25:17
     */
    public Map<String, Object> trymatch(String jsonMenu) {
        return trymatch(loadAccessToken(), jsonMenu);
    }
}
