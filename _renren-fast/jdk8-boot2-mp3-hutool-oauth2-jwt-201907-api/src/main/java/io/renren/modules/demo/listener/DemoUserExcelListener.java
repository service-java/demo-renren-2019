package io.renren.modules.demo.listener;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import io.renren.common.base.exception.RRException;
import io.renren.modules.demo.entity.dto.DemoUserExcelDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2019-09-30 9:23
 */
public class DemoUserExcelListener extends AnalysisEventListener<DemoUserExcelDTO> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<DemoUserExcelDTO> list = new ArrayList<>();

    @Override
    public void invoke(DemoUserExcelDTO data, AnalysisContext context) {

        if (StrUtil.isEmpty(data.getName())) {
            throw new RRException("代表不能为空");
        }
        list.add(data);

//        if (list.size() >= BATCH_COUNT) {
//            saveData();
//            list.clear();
//        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        saveData();
    }

    public List<DemoUserExcelDTO> getList() {
        return list;
    }

    private void saveData() {

    }
}
