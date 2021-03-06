package cn.armylife.union.serviceimpl;

import cn.armylife.union.domain.Favorites;
import cn.armylife.union.domain.SmallImages;
import cn.armylife.union.mapper.FavoritesItemMapper;
import cn.armylife.union.mapper.FavoritesMapper;
import cn.armylife.union.mapper.SmallImagesMapper;
import cn.armylife.union.service.TaobaoService;
import com.github.pagehelper.Page;
import com.taobao.api.domain.UatmTbkItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaobaoServiceImpl implements TaobaoService {
    @Autowired
    FavoritesMapper favoritesMapper;
    @Autowired
    FavoritesItemMapper favoritesItemMapper;
    @Autowired
    SmallImagesMapper smallImagesMapper;

    /**
     * 添加商品选品库列表
     * @param favorites
     * @return
     */
    public int FavoritesInsert(Favorites favorites){
//        favoritesMapper.deleteAll();
        return favoritesMapper.insert(favorites);
    };

    /**
     * 查询全部选品库列表
     * @return
     */
    public List<Favorites> FavoritesAll(){
        return favoritesMapper.selectAll();
    };

    /**
     * 选品库商品
     * @param favoritesItem
     * @return
     */
    public int FavoritesItemInsert(UatmTbkItem favoritesItem){
        return favoritesItemMapper.insert(favoritesItem);
    };

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table favorites_item
     *
     * @mbggenerated
     */
   public Page<UatmTbkItem> FavoritesItemAll(){
       return favoritesItemMapper.selectAll();
   };

    /**
     * 小图添加
     * @param smallImages
     * @return
     */
    public int smallImagesInsert(SmallImages smallImages){
        return smallImagesMapper.insert(smallImages);
    };


    /**
     * 清空选品库列表
     * @return
     */
    public int deleteAll(){
        return favoritesMapper.deleteAll();
    };
}
