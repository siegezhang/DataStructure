package poi;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.MergeCellRule;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.template.MetaTemplate;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PoiTest {
    @Test
    public void test() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "上海金煦文化发展有限公司");
        data.put("start_time", "2021年9月23日");
        XWPFTemplate template = XWPFTemplate.compile("D:/projects/DataStructure/doc/2021.9.23 雍强销售合同模板-金煦文化-深圳13640.docx")
                .render(data);
        FileOutputStream out;
        out = new FileOutputStream("D:/projects/DataStructure/doc/2021.9.23 雍强销售合同模板-金煦文化-深圳13640-1.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

    @Test
    public void testTable() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "上海金煦文化发展有限公司");
        data.put("start_time", "2021年9月23日");
        // 第0行居中且背景为蓝色的表格
        RowRenderData row0 = Rows.of("品名", "产品型号", "单位", "数量", "单价（元）", "小计（元）", "备注").textColor("FFFFFF")
                .bgColor("4472C4").center().create();
        RowRenderData row1 = Rows.create("网络摄像机", "DS-2CD2125EFV2-IS", "台", "9", "700", "6300", "");
        RowRenderData row2 = Rows.create("硬盘录像机", "DS-7716N-K4", "台", "1", "2300", "2300", "");
        RowRenderData row3 = Rows.create("硬盘", "ST4000VX000-520,4TB,5900RPM,3.5,SATA", "个", "2", "900", "1800", "");
        RowRenderData row4 = Rows.create("云眸软件", "海康云眸连锁-视频联网+巡查业务", "路/年", "9", "360", "3240", "云眸按实际激活路数收费，6%发票");
        RowRenderData row5 = Rows.create("设备总价:13640（壹万叁仟陆佰肆拾元整）", null, null, null, null, null, null);
        MergeCellRule rule = MergeCellRule.builder().map(MergeCellRule.Grid.of(5, 0), MergeCellRule.Grid.of(5, 6)).build();

        data.put("table1", Tables.of(row0, row1, row2, row3, row4, row5).mergeRule(rule).create());

        XWPFTemplate template = XWPFTemplate.compile("D:/projects/DataStructure/doc/2021.9.23 雍强销售合同模板-金煦文化-深圳13640.docx");
        List<MetaTemplate> list = template.getElementTemplates();
        template.render(data);
        FileOutputStream out;
        out = new FileOutputStream("D:/projects/DataStructure/doc/2021.9.23 雍强销售合同模板-金煦文化-深圳13640-1.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();

    }
}
