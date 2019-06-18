package com.picaas.manager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.picaas.comm.PageBean;
import com.picaas.manager.entity.Grade;
import com.picaas.manager.entity.Iconography;
import com.picaas.manager.entity.Illness;
import com.picaas.manager.entity.Information;
import com.picaas.manager.entity.Msg;
import com.picaas.manager.entity.PatientInformation;
import com.picaas.manager.service.BasicDataService;

import net.sf.json.JSONObject;
import sun.nio.cs.ext.DoubleByte.Decoder_DBCSONLY;

/**
 * Servlet implementation class BasicDataServlet
 */
@WebServlet("/manager/BasicDataServlet")
public class BasicDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicDataServlet() {
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
		String action = request.getParameter("action");
//		System.out.println("������action: "+action);
		if ("Illness".equals(action)) {
			doQueryIllness(request, response);//��ѯ���߲�ʷ
		} else if ("Iconography".equals(action)) {
			doQueryIconography(request, response);//��ѯӰ��ѧ���
		} else if ("PF".equals(action)) {
			doQueryPF(request, response);//��ѯ���л�����Ϣ
		} else if("Grade".equals(action)){
			doQueryGrade(request, response);//��ѯ���߳ɼ�
		} else if("ajaxQueryGrade".equals(action)){
			doAQueryGrade(request, response);
		}
		
	}
	
	/**
	 * ��ѯ���߳ɼ�
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doQueryGrade(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String iuid = request.getParameter("iuid") == null ? "" : request.getParameter("iuid");	
		BasicDataService bds = new BasicDataService();
		List<Grade> list = bds.doQueryGrade(iuid);
		if (list == null) {
			Msg msg = new Msg();
			msg.setFlag(0);
			msg.setContents("���ݿ�û�����ݣ�����");
			request.getSession().setAttribute("errorMsg", msg);
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}else {
			session.setAttribute("grade", list);
			System.out.println(list);
			request.getRequestDispatcher("grade/scorelist_score.jsp").forward(request, response);

		}
	}

	/**
	 * ���»����ܳɼ�
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
//	private void doCollectScore(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		int i = 0;
//		BasicDataService bds = new BasicDataService();
//		//��������ѧ���ɼ�
//		List<CollectScore> scoreList = bds.doCollectScore();
//		if (scoreList == null) {
//			return;
//		}else {
//			//����ѧ��IUID����ѧ����Ϣ���е�totalScore
//			for (CollectScore collectScore : scoreList) {
//				
//				int rs = bds.doUpdateStuScoreByIUID(collectScore.getStudentsIUID(), collectScore.getTotalScore());
//				i++;
//				if (i == scoreList.size()) {
//					//�鿴����ѧ����Ϣ
//					doConditionQueryStudents(request, response);
//				}
//			}
//		}
//		
//	}

	//��������ѯ������Ϣ
//	private void doConditionQueryStudents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		try {
//			//��ǰ�ǵ�ҳ��
//			String currPage = request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage");
//			int currentPage = Integer.parseInt(currPage);
//			//����PageBean�������õ�ǰҳ������ ����service��������
//			PageBean<Students> page = new PageBean<Students>();
//			page.setCurrentPage(currentPage);
//			request.setCharacterEncoding("utf-8");
//			response.setCharacterEncoding("utf-8");
//			String schoolName = request.getParameter("schoolName") == null ? "" : 
//				new String(request.getParameter("schoolName").getBytes("iso-8859-1"), "utf-8");
//			String idc = request.getParameter("idCard") == null ? "" : request.getParameter("idCard");
//			System.out.println("�յ�������ֵ��" + schoolName +"------------"+idc);
//			String idCard = idc;
//			BasicDataService basicDataService = new BasicDataService();
//			
//			PageBean<Students> pageBean = basicDataService.doConditionQueryStudents(page, idCard, schoolName);
//			//��ѯ����ѧУ
//			List<School> schools = basicDataService.doQuerySchool();
//			
//			HttpSession session = request.getSession();
//			if (pageBean == null) {
//				Msg msg = new Msg();
//				msg.setFlag(0);
//				msg.setContents("���ݿ�û�����ݣ�����");
//				request.getSession().setAttribute("errorMsg", msg);
//				response.sendRedirect(request.getContextPath()+"/error.jsp");
//			} else if (pageBean.getPageData() != null && !pageBean.getPageData().isEmpty()) {
//				
//				session.setAttribute("schools", schools);
//				session.setAttribute("pageStudent", pageBean);
//				session.setAttribute("idCard", idCard);
//				session.setAttribute("schoolName", schoolName);
////				response.getWriter().write(JsonTools.JsonToString("success", "OK"));
//				request.getRequestDispatcher("basicData/students_result.jsp").forward(request, response);
//			}
//			
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

//	// �޸Ļ�����Ϣ
//	private void doAUpStudents(HttpServletRequest request, HttpServletResponse response) {
//		response.setCharacterEncoding("utf-8");	
//		try {
//			String idCard = request.getParameter("newVal");
//			int IUID = Integer.valueOf(request.getParameter("IUID"));
//			System.out.println(idCard+"     "+IUID);
//			
//			PrintWriter printWriter = response.getWriter();
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("name", "����");
//			printWriter.write(jsonObject.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * ��ҳ��ѯ���л�����Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doQueryPF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ǰ�ǵ�ҳ��
		String currPage = request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage");
		int currentPage = Integer.parseInt(currPage);
		//����PageBean�������õ�ǰҳ������ ����service��������
		PageBean<PatientInformation> page = new PageBean<PatientInformation>();
		page.setCurrentPage(currentPage);

		BasicDataService basicDataService = new BasicDataService();
//		List<Students> list = basicDataService.doQueryStudents();
		PageBean<PatientInformation> pageBean = basicDataService.doPagedQueryPatientInformation(page);
		HttpSession session = request.getSession();
		List<PatientInformation> list = pageBean.getPageData();
		if (list != null && !list.isEmpty()) {
			session.setAttribute("PatientInformation", pageBean);
			request.getRequestDispatcher("basicData/patientInformation_list.jsp").forward(request, response);
		}else {
			session.setAttribute("errorMsg", "���ݿ�û�����ݣ�����");
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
	}
	// ��ѯ�ɼ�
	private void doAQueryGrade(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("���˳ɼ���ѯ");
		response.setCharacterEncoding("utf-8");	
		try {
			String round = request.getParameter("round");
			String IUID = request.getParameter("IUID");
			System.out.println(round+"     "+IUID);
			
			PrintWriter printWriter = response.getWriter();
//					JSONObject jsonObject = new JSONObject();
			BasicDataService basicDataService = new BasicDataService();
			List<Information> list = basicDataService.doAUpGrade(round, IUID);
			System.out.println("list size is " + list.size());
			JSONObject jsonObject = new JSONObject();
//					for(Object obj:list){
//						System.out.println(obj);
//					}
			jsonObject.put("Object", list);
			System.out.println(list);
			printWriter.write(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ѯ���߲�ʷ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doQueryIllness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicDataService basicDataService = new BasicDataService();
		List<Illness> list = basicDataService.doQueryIllness();
		
		HttpSession session = request.getSession();
		if (list.size()>=0) {
			System.out.println("���л��ߣ�"+list.size());
//			ModuleClass moduleClass = list.get(0);
			session.setAttribute("Illness", list);
//			response.sendRedirect(request.getContextPath()+"/manager/basicData/moduleclass_list.jsp");
			request.getRequestDispatcher("basicData/Illness_list.jsp").forward(request, response);
		}
	}
	
	
	/**
	 * ��ѯӰ��ѧ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void doQueryIconography(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicDataService basicDataService = new BasicDataService();
		List<Iconography> list = basicDataService.doQueryIconography();
		HttpSession session = request.getSession();
		if (list.size()>=0) {
//			ModuleClass moduleClass = list.get(0);
			session.setAttribute("Iconography", list);
			request.getRequestDispatcher("basicData/Iconography_list.jsp").forward(request, response);
		}
	}

}
