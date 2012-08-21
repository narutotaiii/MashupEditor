package restfulService.types;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.*;
import java.io.*;

public class WriteXML
{
    public void BuildXML() throws Exception
    {
        Element root,student,number,name,age;
                
        root = new Element("student-info"); //生成根元素：student-info   
        student = new Element("student");      //生成元素：student,该元素中将包含元素number,name,age
        number = new Element("number");
        name = new Element("name");
        age = new Element("age");
        
        Document doc = new Document(root);    //将根元素植入文档doc中
        
        number.setText("001456");
        name.setText("lnma2513n");
        age.setText("24513");
        student.addContent(number);
        student.addContent(name);
        student.addContent(age);
        root.addContent(student);
        
        Format format = Format.getCompactFormat();
        format.setEncoding("gb2312");           //设置xml文件的字符为gb2312
        format.setIndent("    ");               //设置xml文件的缩进为4个空格
        
        XMLOutputter XMLOut = new XMLOutputter(format);//在元素后换行，每一层元素缩排四格 
        XMLOut.output(doc, new FileOutputStream("studentinfo.xml",true));  
        
    }
    public void readAndWrite() throws JDOMException, IOException
    {
    	SAXBuilder builder = new SAXBuilder();
        Document read_doc = builder.build("studentinfo.xml");
        Element stu = read_doc.getRootElement();
        Element root,student,number,name,age;
        
        root = new Element("new1"); //生成根元素：student-info   
        student = new Element("new2");      //生成元素：student,该元素中将包含元素number,name,age
        number = new Element("new3");
        name = new Element("new4");
        age = new Element("new5");
        stu.addContent(root);
        stu.addContent(student);
        stu.addContent(number);
        XMLOutputter XMLOut = new XMLOutputter();//在元素后换行，每一层元素缩排四格 
        XMLOut.output(read_doc, new FileOutputStream("studentinfo.xml",false));
    }
    public static void main(String[] args) throws Exception
    {
        WriteXML w = new WriteXML();
        System.out.println("Now we build an XML document .....");
        w.BuildXML();
        System.out.println("finished!");
    }
 
}
