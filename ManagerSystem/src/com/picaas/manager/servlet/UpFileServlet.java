package com.picaas.manager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.picaas.comm.ReadWriteExcel;
import com.picaas.manager.entity.ExcelGrade;
import com.picaas.manager.entity.ExcelPatientInformation;
import com.picaas.manager.entity.PatientInformation;
import com.picaas.manager.service.BasicDataService;



/**
 * 
 * 文件上传
 * Servlet implementation class UpFile
 */
@WebServlet("/manager/UpFile")
@MultipartConfig
public class UpFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
//		System.out.println(action);
		if ("PF".equals(action)) {
			uploadPF(request, response);
		} else if("Illness".equals(action)){
			uploadIllness(request, response);
		} else if("Iconography".equals(action)){
			uploadIconography(request, response);
		} else if("grade".equals(action)){
			uploadGrade(request, response);
		}
		//        request.getRequestDispatcher("/upload.jsp").forward(request, response);       
	}
	
	/**
	 * 上传患者信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void uploadPF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//            System.out.println("123");

            //获取文件描述信息
            String desc=request.getParameter("desc");
            //获取上传的文件
            Part part=request.getPart("file");
            //获取请求的信息
            String name=part.getHeader("content-disposition");
            System.out.println(name);//测试使用
//            System.out.println(desc);//
            
            //获取上传文件的目录
            String root=request.getServletContext().getRealPath("/upload");
//            System.out.println("测试上传文件的路径："+root);
            
            //获取文件名
//            String realname = new String(name.substring(name.indexOf("filename=\"") + 10,
//            		name.lastIndexOf(".")).getBytes("gb2312"), "iso8859-1");
//            System.out.println("文件名："+realname);
            //获取文件的后缀
            String str=name.substring(name.lastIndexOf("."), name.length()-1);
//            System.out.println("测试获取文件的后缀："+str);
            
            //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
//            String filename=root+"\\"+realname+"-"+UUID.randomUUID().toString()+str;
            String fn = UUID.randomUUID().toString()+str;
            String filename=root+"\\"+ fn;
//            System.out.println("测试产生新的文件名："+filename);
            
            //上传文件到指定目录，不想上传文件就不调用这个
            part.write(filename);
            ReadWriteExcel rwe = new ReadWriteExcel();
            //读取excel文件并存入数据库
            List<Object> list = rwe.getPFData(request, response, fn);
            BasicDataService basicDataService = new BasicDataService();
            List<Object> l = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
				//把从excel中读到list中的数据存入数据库
            	for (int i=0; i<list.size(); i++){
            		System.out.println("for"+list.get(i));
                	ExcelPatientInformation p = (ExcelPatientInformation)list.get(i);
                	List<PatientInformation> lp = basicDataService.doQueryStudentsByIDCard(p.getIUID());
                	if(lp == null || lp.isEmpty()){
                		l.add(list.get(i));
                		int rs = basicDataService.addPFForList(l);
                	}
                }
			}
            request.getRequestDispatcher("/manager/BasicDataServlet?action=PF").forward(request, response);
            request.setAttribute("uploadinfo", "上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "上传文件失败");
        }

		
	}
	
	/**
	 * 上传患者病史
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void uploadIllness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            //获取文件描述信息
            String desc=request.getParameter("desc");
            //获取上传的文件
            Part part=request.getPart("file");
            //获取请求的信息
            String name=part.getHeader("content-disposition");
//            System.out.println(name);//测试使用
//            System.out.println(desc);//
            
            //获取上传文件的目录
            String root=request.getServletContext().getRealPath("/upload");
//            System.out.println("测试上传文件的路径："+root);
            
            //获取文件名
//            String realname = new String(name.substring(name.indexOf("filename=\"") + 10,
//            		name.lastIndexOf(".")).getBytes("gb2312"), "iso8859-1");
//            System.out.println("文件名："+realname);
            //获取文件的后缀
            String str=name.substring(name.lastIndexOf("."), name.length()-1);
//            System.out.println("测试获取文件的后缀："+str);
            
            //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
//            String filename=root+"\\"+realname+"-"+UUID.randomUUID().toString()+str;
            String fn = UUID.randomUUID().toString()+str;
            String filename=root+"\\"+ fn;
//            System.out.println("测试产生新的文件名："+filename);
            
            //上传文件到指定目录，不想上传文件就不调用这个
            part.write(filename);
            ReadWriteExcel rwe = new ReadWriteExcel();
            //读取excel文件并存入数据库
            List<Object> list = rwe.getIllnessData(request, response, fn);
            if (list != null && !list.isEmpty()) {
				//把从excel中读到list中的数据存入数据库
            	BasicDataService basicDataService = new BasicDataService();
            	int rs = basicDataService.addIllnessForList(list);
            	if (rs == 1) {
            		request.getRequestDispatcher("/manager/BasicDataServlet?action=Illness").forward(request, response);					
				}
			}
            request.setAttribute("uploadinfo", "上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "上传文件失败");
        }

		
	}
	
	/**
	 * 上传影像学
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void uploadIconography(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            //获取文件描述信息
            String desc=request.getParameter("desc");
            //获取上传的文件
            Part part=request.getPart("file");
            //获取请求的信息
            String name=part.getHeader("content-disposition");
//            System.out.println(name);//测试使用
//            System.out.println(desc);//
            
            //获取上传文件的目录
            String root=request.getServletContext().getRealPath("/upload");
//            System.out.println("测试上传文件的路径："+root);
            
            //获取文件名
//            String realname = new String(name.substring(name.indexOf("filename=\"") + 10,
//            		name.lastIndexOf(".")).getBytes("gb2312"), "iso8859-1");
//            System.out.println("文件名："+realname);
            //获取文件的后缀
            String str=name.substring(name.lastIndexOf("."), name.length()-1);
//            System.out.println("测试获取文件的后缀："+str);
            
            //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
//            String filename=root+"\\"+realname+"-"+UUID.randomUUID().toString()+str;
            String fn = UUID.randomUUID().toString()+str;
            String filename=root+"\\"+ fn;
//            System.out.println("测试产生新的文件名："+filename);
            
            //上传文件到指定目录，不想上传文件就不调用这个
            part.write(filename);
            ReadWriteExcel rwe = new ReadWriteExcel();
            //读取excel文件并存入数据库
            List<Object> list = rwe.getIconographyData(request, response, fn);
            if (list != null && !list.isEmpty()) {
				//把从excel中读到list中的数据存入数据库
            	BasicDataService basicDataService = new BasicDataService();
            	int rs = basicDataService.addIconographyForList(list);
            	if (rs == 1) {
            		request.getRequestDispatcher("/manager/BasicDataServlet?action=Iconography").forward(request, response);					
				}
			}
            request.setAttribute("uploadinfo", "上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "上传文件失败");
        }

		
	}
	
	/**
	 * 上传患者成绩
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void uploadGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            //获取文件描述信息
            String desc=request.getParameter("desc");
            //获取上传的文件
            Part part=request.getPart("file");
            //获取请求的信息
            String name=part.getHeader("content-disposition");
//            System.out.println(name);//测试使用
//            System.out.println(desc);//
            
            //获取上传文件的目录
            String root=request.getServletContext().getRealPath("/upload");
//            System.out.println("测试上传文件的路径："+root);
            
            //获取文件名
//            String realname = new String(name.substring(name.indexOf("filename=\"") + 10,
//            		name.lastIndexOf(".")).getBytes("gb2312"), "iso8859-1");
//            System.out.println("文件名："+realname);
            //获取文件的后缀
            String str=name.substring(name.lastIndexOf("."), name.length()-1);
//            System.out.println("测试获取文件的后缀："+str);
            
            //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
//            String filename=root+"\\"+realname+"-"+UUID.randomUUID().toString()+str;
            String fn = UUID.randomUUID().toString()+str;
            String filename=root+"\\"+ fn;
//            System.out.println("测试产生新的文件名："+filename);
            
            //上传文件到指定目录，不想上传文件就不调用这个
            part.write(filename);
            ReadWriteExcel rwe = new ReadWriteExcel();
            //读取excel文件并存入数据库
            List<ExcelGrade> list = rwe.getGradeData(request, response, fn);
            BasicDataService basicDataService = new BasicDataService();
//            System.out.println("ExcelGrade1 :"+list.get(0).getContent().get(0).toString());
//            System.out.println("ExcelGrade2 :"+list.get(0).getContent().get(1).toString());
//            System.out.println("ExcelGrade3 :"+list.get(0).getContent().get(2).toString());
//            System.out.println("ExcelGrade3 :"+list.get(0).getContent().get(3).toString());
            
//            for(ExcelGrade grade: list){
//            	List<List<String>> lists = grade.getContent();
//            	for(List<String> string : lists){
//            		for(String strings:string){
//            			System.out.println("strings:  " +strings);
//            		}
//            	}
//            }
//          System.out.println("ExcelGrade1 :"+list.get(0).getContent().toString());
            //拿到每张问卷的ID
//            List<String> qID =  basicDataService.doQueryQuestionnaire(list.get(0).getContent().get(0));
//            System.out.println("问卷IUID："+qID.toString());
//            System.out.println(list.toString());
            basicDataService.doAddGrade(list.get(0).getContent());
            if (list != null && !list.isEmpty()) {
				//把从excel中读到list中的数据存入数据库
            	int rs = 1;
            	if (rs == 1) {
            		request.getRequestDispatcher("/manager/BasicDataServlet?action=Grade").forward(request, response);					
				}
			}
            request.setAttribute("uploadinfo", "上传文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "上传文件失败");
        }

		
	}

}
