package net.newcapec.sca.report.classic;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class ManipulateXMLFile {

    public List initBaseDemoInfo()
    {
        List topList = new ArrayList();

        List list1 = new ArrayList();
        list1.add("起点：海安");
        topList.add(list1);

        List list2 = new ArrayList();
        list2.add("公里");
        list2.add("票价");
        topList.add(list2);

        List list3 = new ArrayList();
        list3.add("7");
        list3.add("2");
        list3.add("胡中口");
        topList.add(list3);

        List list4 = new ArrayList();
        list4.add("11");
        list4.add("2.5");
        list4.add("2");
        list4.add("胡集");
        topList.add(list4);

        List list5 = new ArrayList();
        list5.add("14");
        list5.add("3");
        list5.add("2");
        list5.add("2");
        list5.add("双楼路口");
        topList.add(list5);

        List<String> list6 = new ArrayList();
        list6.add("17");
        list6.add("3.5");
        list6.add("2");
        list6.add("2");
        list6.add("2");
        list6.add("曲唐");
        topList.add(list6);

        return topList;
    }
    public  org.dom4j.Document manipulateListInfo2Report(
            List<List> list) {
        int lineNum = list.size() + 3;
        int viewLineNum = list.size() + 3;

        //需要打印的列数
        int closNum = list.get(viewLineNum-4).size()+2;
        //最小行数为9行
        lineNum = lineNum > 9 ? lineNum: 9;
        closNum = closNum > 9 ? closNum : 9;

        //定为结尾元素的位置
        int y1 = lineNum*24;
        int x1 = 53;
        int x2 = 634;
        int y2 = 63 + y1;

        org.dom4j.Document doc = loadConfigByFileName("1_1.xml");

        Element baseDom = doc.getRootElement().element("WorkSheet");
        Element aimDom = baseDom.element("Table");
        for(int i=0; i < closNum ; i++)
        {
            if(i == 0 || i ==closNum -1)
            {
                aimDom.addElement("Col").addAttribute("width", "15");
            }
            else
            {
                aimDom.addElement("Col").addAttribute("width", "140");
            }
        }
        for(int i=0; i < lineNum ; i++)
        {
            Element tr1 = aimDom.addElement("TR");
            tr1.addAttribute("height", "24");
            tr1.addAttribute("sequence", ""+i);

            tr1.addElement("TD");
            if(i == 0)
            {
                mainpuldateTitleNode(closNum, tr1);
            }
            else if(i== 1)
            {
                manipulateFirstNode(closNum, tr1,list.get((i-1)));
            }
            else if( i == (viewLineNum -2))
            {
                manipulateLastNode(tr1,viewLineNum,closNum);
            }
            else
            {
                int m = i-1;
                if(m < list.size())
                {
                    if(m<5)
                    {
                        manipulateCenterNode_withTips(m,tr1,closNum,list.get(m));
                    }
                    else
                    {
                        manipulateCenterNode(tr1,closNum,list.get(m));
                    }
                }
                else
                {
                    manipulateEmptyCols(closNum, tr1);
                }
            }
        }
        Element rect = doc.getRootElement().element("WorkSheet").element("GraphicObjects").element("TextBox").element("Rect");
        rect.addAttribute("x1", ""+x1);
        rect.addAttribute("y1", ""+y1);
        rect.addAttribute("x2", ""+x2);
        rect.addAttribute("y2", ""+y2);
        return doc;
    }
    private void manipulateCenterNode_withTips(int col,Element tr,int step,List<String> list)
    {
        //第二行
        if(col == 1)
        {
            for(int i= 0; i < (step-1) ; i ++)
             {
                Element td = tr.addElement("TD");
                if(i == 5)
                 {
                     td.setText("行驶路线");
                     td.addAttribute("leftBorder", "1");
                     td.addAttribute("topBorder", "1");
                     td.addAttribute("align", "center");
                 }
                 else if(i == 6)
                 {
                     td.setText("海安至曲唐");
                     td.addAttribute("leftBorder", "1");
                     td.addAttribute("topBorder", "1");
                     td.addAttribute("align", "center");
                 }
                 else if(i == 7)
                 {
                     td.addAttribute("leftBorder", "1");
                 }
                manipulateCenterTOAddTips(list, i, td);
            }
        }
        else if(col == 2)
        {
            for(int i= 0; i <= (step-1) ; i ++)
             {
                if(i == 6)
                {
                    continue;
                }
                Element td = tr.addElement("TD");
                if(i == 5)
                 {
                    td.setAttributeValue("clospan", "2");
                    td.addAttribute("leftBorder", "1");
                    td.addAttribute("topBorder", "1");
                    td.addAttribute("align", "center");
                    td.setText("核定单位盖章");
                 }
                if(i == 7)
                 {
                     td.addAttribute("leftBorder", "1");
                 }
                if(i == 8)
                 {
                     td.addAttribute("leftBorder", "1");
                 }
                manipulateCenterTOAddTips(list, i, td);
            }
        }
        else if(col == 3)
        {
            for(int i= 0; i < (step-1) ; i ++)
             {
                Element td = tr.addElement("TD");
                if(i == 5)
                 {
                     td.setText("年 月 ");
                     td.addAttribute("leftBorder", "1");
                     td.addAttribute("topBorder", "1");
                     td.addAttribute("align", "center");
                 }
                 else if(i == 6)
                 {
                     td.setText(" 年 月 ");
                     td.addAttribute("leftBorder", "1");
                     td.addAttribute("topBorder", "1");
                     td.addAttribute("align", "center");
                 }
                 else if(i == 7)
                 {
                     td.addAttribute("leftBorder", "1");
                 }
                manipulateCenterTOAddTips(list, i, td);
            }
        }
        else if(col == 4)
        {
            for(int i= 0; i < (step-1) ; i ++)
             {
                Element td = tr.addElement("TD");
                if(i == 5)
                 {
                     td.addAttribute("topBorder", "1");
                 }
                 else if(i == 6)
                 {
                     td.addAttribute("topBorder", "1");
                 }

                manipulateCenterTOAddTips(list, i, td);
            }
        }
    }
     private  org.dom4j.Document loadConfigByFileName(String filename)
        {
            org.dom4j.Document document = null;
            try
            {
                SAXReader saxReader = new SAXReader();
                document = saxReader.read(this.getClass().getClassLoader().getResourceAsStream(filename));
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            return document;
        }

    private void manipulateCenterNode(Element tr, Integer step,
            List<String> list) {
        for(int i= 0; i < (step-1) ; i ++)
         {
            Element td = tr.addElement("TD");
            manipulateCenterTOAddTips(list, i, td);
        }

    }
    private void manipulateCenterTOAddTips(List<String> list, int i, Element td) {
        //如果当前循环小于list说明可以往td中添加内容，如上打印空格
        if(i >= list.size())
        {
            return;
        }

        //此时list有值

        //list长度小于2：左边框为0，上边框为0
        if(list.size() > 2)
        {
            //list中最后一个元素不用设置上边线框粗细值
            if(i == (list.size() -1))
            {
                td.addAttribute("leftBorder", "1");
                td.addAttribute("align", "center");
                td.setText(list.get(i));
            }
            else
            {
                //list中其他的元素都需要设置
                td.addAttribute("leftBorder", "1");
                td.addAttribute("topBorder", "1");
                td.addAttribute("align", "center");
                td.setText(list.get(i));
            }
        }
        else
        {
            td.addAttribute("align", "center");
            td.setText(list.get(i));
        }
    }

    private void manipulateLastNode(Element tr, Integer step,int lines) {
        for(int i= 0; i < (lines-2); i ++)
         {
             if(i >= step -4 )
             {
                 tr.addElement("TD");
             }
             else
             {
                 tr.addElement("TD").addAttribute("topBorder", "1");
             }
         }
    }
    private void manipulateEmptyCols(int lineNum, Element tr1) {
        for(int step= 0; step < (lineNum-1) ; step ++)
         {
                 tr1.addElement("TD");
         }
    }
    private void mainpuldateTitleNode(int lineNum, Element tr1)
    {
        for(int step= 0; step < (lineNum-1) ; step ++)
         {
            Element td = tr1.addElement("TD");
            if(step == 0)
            {
                td.addAttribute("fontIndex", "1");
                td.addAttribute("align", "center");
                td.setText("海安县城乡公交班线票价核定表");
            }
         }
    }
    private void manipulateFirstNode(int lineNum, Element tr1,List<String> list) {

        for(int step= 0; step < (lineNum-1) ; step ++)
         {

            Element td = tr1.addElement("TD");
            if(step == 0 )
            {
                td.addAttribute("align", "center");
                td.setText(list.get(0));
            }
            if(step == 5)
             {
                 td.addAttribute("leftBorder", "1");
                 td.addAttribute("topBorder", "1");
                 td.addAttribute("align", "center");
                 td.setText("车属单位");
             }
             else if(step == 6)
             {
                 td.addAttribute("leftBorder", "1");
                 td.addAttribute("topBorder", "1");
                 td.addAttribute("align", "center");
                 td.setText("海安县飞鹤公共交通有限公司");
             }
             else if(step == 7)
             {
                 td.addAttribute("leftBorder", "1");
             }
         }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ManipulateXMLFile mx = new ManipulateXMLFile();

        List<List> list = mx.initBaseDemoInfo();

        org.dom4j.Document doc = mx.manipulateListInfo2Report(list);

        System.out.println(doc.asXML());
    }


}
