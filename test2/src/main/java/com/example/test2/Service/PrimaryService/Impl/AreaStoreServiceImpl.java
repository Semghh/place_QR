package com.example.test2.Service.PrimaryService.Impl;

import com.example.test2.Mapper.Primary.AreaStoreTableMapper;
import com.example.test2.POJO.Area;
import com.example.test2.POJO.AreaStoreTable;
import com.example.test2.Service.PrimaryService.AreaStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service("AreaStoreService")
public class AreaStoreServiceImpl implements AreaStoreService {

    private AreaStoreTableMapper areaStoreTableMapper;

    @Autowired
    public AreaStoreServiceImpl(AreaStoreTableMapper areaStoreTableMapper) {
        this.areaStoreTableMapper = areaStoreTableMapper;
    }

    public AreaStoreTable getAreaById(Long area_id) {
        return areaStoreTableMapper.getAreaById(area_id);
    }

    public boolean isExistArea(Long area_id) {
        return areaStoreTableMapper.isExistArea(area_id) == 1;
    }

    @Override
    public int AreaConvertToStore(Area area) {
        convertToAreaStoreHandler convertToAreaStoreHandler = new convertToAreaStoreHandler(areaStoreTableMapper, area);
        int res = convertToAreaStoreHandler.getRes();
        return res;
    }

    @Override
    public List<Area> travelArea(Area area) {
        travelArea travelArea = new travelArea(area);
        List<Area> listTravel = travelArea.getListTravel();
        return listTravel;
    }


    public Area convertToArea(Long area_id) {
        return new ConvertToAreaHandler(area_id, areaStoreTableMapper).getRes();
    }


    /**
     * 内部工具类，将存储结构转换为Area
     */
    static class ConvertToAreaHandler {

        private Long area_id;
        private AreaStoreTableMapper areaStoreTableMapper;

        public ConvertToAreaHandler(Long area_id, AreaStoreTableMapper areaStoreTableMapper) {
            this.area_id = area_id;
            this.areaStoreTableMapper = areaStoreTableMapper;
        }

        public Area recursion(Long area_id, Area father) {

            AreaStoreTable store = areaStoreTableMapper.getAreaById(area_id);

            Long id = store.getId();
            String name = store.getName();
            Integer area_level = store.getArea_level();
            Integer risk_level = store.getRisk_level();

            Area cur = new Area(id, name, risk_level, area_level, null, father);


            if (store.getChildren() != null && store.getChildren().length() > 0) {
                Long[] childIds = Area.convertChildStringToIdArray(store.getChildren());
                Area[] ch = new Area[childIds.length];
                for (int i = 0; i < ch.length; i++) {
                    ch[i] = recursion(childIds[i], cur);
                }
                cur.setChildren(ch);
            }
            return cur;
        }


        public Area getRes() {
            return recursion(area_id, null);
        }


    }

    /**
     * 内部工具类，将Area转化为Store
     *
     * 仅适用于 第一次插入的节点。
     */
    static class convertToAreaStoreHandler {
        private AreaStoreTableMapper storeTableMapper;
        private Area menu;
        private int successfulTimes = 0;

        public convertToAreaStoreHandler(AreaStoreTableMapper storeTableMapper, Area menu) {
            this.storeTableMapper = storeTableMapper;
            this.menu = menu;
        }

        //递归插入
        private Long recursion(Area area, Long fatherId) {

            AreaStoreTable store = new AreaStoreTable();
            store.setId(area.getId());
            store.setArea_level(area.getArea_level());
//            if (area.getFather()!=null) {
//                store.setFather_id(area.getFather().getId());
//            }
            if (fatherId != null) store.setFather_id(fatherId);
            store.setName(area.getName());
            store.setRisk_level(area.getRisk_level());

            Area[] children = area.getChildren();
            Long curId = null;
            if (children == null || children.length <= 0) {
                int i = storeTableMapper.insertNewAreaStore(store); //叶子节点插入数据库
                if (i == 1) {
                    successfulTimes++;
                    curId = storeTableMapper.getLastId();
                }
                return curId;
            }
            //有子节点时
            StringBuilder childStr = new StringBuilder();
            for (int j = 0; j < children.length; j++) {
                Long son_id = recursion(children[j], curId);
                childStr.append(son_id);
                childStr.append(",");
            }
            StringBuilder stringBuilder = childStr.deleteCharAt(childStr.length() - 1);
            store.setChildren(stringBuilder.toString());
            store.setId(null);
            store.setArea_level(null);
            store.setFather_id(null);
            store.setName(null);
            store.setRisk_level(null);
            int i = storeTableMapper.dynamicUpdate(store); //插入当前节点

            return curId;
        }

        public int getRes() {
            recursion(menu, null);
            return successfulTimes;
        }

        private boolean checkSafe(Long id) {
            if (id == null) return true;
            if (storeTableMapper.isExistArea(id) == 1) {
                return false;
            }
            return true;
        }
    }


    static class travelArea {
        List<Area> res;
        Area root;

        public travelArea(Area root) {
            this.root = root;
            res = new LinkedList<>();
            recursion(root);
        }

        public String getStringTravel() {
            return res.toString();
        }

        public List<Area> getListTravel() {
            return res;
        }

        private void recursion(Area root) {
            if (root == null) return;
            res.add(root);
            Area[] children = root.getChildren();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    recursion(children[i]);
                }
            }
        }
    }

    @Component()
    public static class AreaTreeUtil{

        @Resource(name = "areaTree")
        Area root;

        private List<Area> getNodeByFatherId(Area root,List<Area> res,Long fatherId){
            if (root.getFather()!=null && root.getFather().getId().equals(fatherId)){
                res.add(root);
                return res;
            }

            Area[] children = root.getChildren();
            if (children==null)return res;
            for (int i = 0; i < children.length; i++) {
                getNodeByFatherId(children[i],res,fatherId);
            }
            return res;
        }

        /**
         * 通过父类id，找到所有子类Area
         * @param fatherId 父类id
         * @return
         */
        public List<Area> getNodeByFatherId(Long fatherId){
            LinkedList<Area> res = new LinkedList<>();
            return getNodeByFatherId(root,res,fatherId);
        }

        public List<Area> getMaxArea(){
            return Arrays.asList(root.getChildren());
        }




    }
}
