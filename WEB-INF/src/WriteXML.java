
import org.jdom.*;
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
        
        number.setText("0021");
        name.setText("lnman1234");
        age.setText("256464");
        student.addContent(number);
        student.addContent(name);
        student.addContent(age);
        root.addContent(student);
        
        Format format = Format.getCompactFormat();
        format.setEncoding("gb2312");           //设置xml文件的字符为gb2312
        format.setIndent("    ");               //设置xml文件的缩进为4个空格
        
        XMLOutputter XMLOut = new XMLOutputter(format);//在元素后换行，每一层元素缩排四格 
        XMLOut.output(doc, new FileOutputStream("studentinfo1.xml"));  
        
    }
    
    public static void main(String[] args) throws Exception
    {
        WriteXML w = new WriteXML();
        System.out.println("Now we build an XML document .....");
        w.BuildXML();
        System.out.println("finished!");
    }
 
}
