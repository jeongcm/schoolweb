<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- menu -->
<div class="menu">
	<font size="2">
	<%
		if (id.equals("학사관리과")) {
	%>
		
		<ul class="depth_1">
			<li><a href="./재학생현황/재학생현황.jsp">1.1 [재학생 증원율]</a></li>
			<li><a href="./강의담당/강의담당.jsp">1.7 [전임 교원 강의담당 비율]</a></li>
			<li><a href="./강의담당상세/강의담당상세.jsp">1.7-1 [전임 교원 강의담당 비율 상세자료]</a></li>
			<li><a href="./중도탈락률/중도탈락률.jsp">2.1 [중도 탈락률]</a></li>
			<li><a href="./캡스톤디자인/캡스톤디자인.jsp">3.4 [캡스톤 디자인 이수학생 비율]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
		
	<%
		} else if (id.equals("교무과") || id.equals("대학원")) {
	%>
		<ul class="depth_1">
			<li><a href="./전임교원/전임교원확보율.jsp">2.4 [전임교원 확보율]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>	
	
	<%
		} else if (id.equals("교수학습개발원")) {
	%>
	
		<ul class="depth_1">
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./강의공개실적/강의공개실적.jsp">3.10 [강의 공개 실적]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
	
	<%
		} else if (id.equals("국제교류본부")) {
	%>
	
		<ul class="depth_1">
			<li><a href="./외국인학생비율/외국인학생비율.jsp">3.1 [외국인 학생 비율]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
	
	<%
		} else if (id.equals("기획과")) {
	%>
		<ul class="depth_1">
			<li><a href="./전임교원1인당국책사업수주실적/전임교원1인당국책사업수주실적.jsp">1.6 [국책사업수주실적]</a></li>
			<li><a href="./발전기금/발전기금조성액.jsp">2.3 [발전기금 조성액]</a></li>
			<li><a href="./발전기금상세/발전기금조성액상세.jsp">2.3-1 [발전기금 조성액상세자료]</a></li>
			<li><a href="./설문조사/설문조사.jsp">3.2 [학생 만족도 평가]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
	
		외부지표
		<ul class="depth_1">
			<li><a href="./신입생현황외부/신입생현황외부.jsp">외부지표_신입생</a></li>
			<li><a href="./중도탈락률외부/중도탈락률외부.jsp">외부지표_중도탈락률</a></li>
			<li><a href="./취업자외부/취업자외부.jsp">외부지표_취업자</a></li>
			<li><a href="./취업제외자외부/취업제외자외부.jsp">외부지표_취업제외자</a></li>
			<li><a href="./취업외부/취업현황외부.jsp">외부지표_취업현황</a></li>
			<li><a href="./연구실적외부/연구실적외부.jsp">외부지표_연구실적</a></li>
			<li><a href="./연구비외부/연구비외부.jsp">외부지표_연구비</a></li>
			<li><a href="./강의담당외부/강의담당외부.jsp">외부지표_강의담당</a></li>
		</ul>
	
	<%
		} else if (id.equals("산학협력단")) {
	%>
		
		<ul class="depth_1">
			<li><a href="./연구실적/연구실적.jsp">1.4 [전임교원 1인당 연구실적]</a></li>
			<li><a href="./연구비/연구비.jsp">1.4 [전임교원 1인당 교외 연구비]</a></li>
			<li><a href="./현장실습/현장실습.jsp">3.3 [현장실습 이수학생]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./특허등록/특허등록.jsp">3.7 [특허등록및 기술이전 수입료]</a></li>
			<li><a href="./특허등록상세/특허등록상세.jsp">3.7-1 [상세자료(특허등록)]</a></li>
			<li><a href="./기술이전/기술이전.jsp">3.7-2 [상세자료(기술이전)]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
		
	<%
		} else if (id.equals("입학관리과")) {
	%>
		
		<ul class="depth_1">
			<li><a href="./신입생현황/신입생현황.jsp">1.2 [정원내 신입생 충원율]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
	
	<%
		} else if (id.substring(id.length() - 2, id.length()).equals("학과")
					|| id.substring(id.length() - 2, id.length()).equals("전공")
					|| id.substring(id.length() - 2, id.length()).equals("학부")) {
	%>
	
		<ul class="depth_1">
			<li><a href="./수상실적/수상실적상세자료.jsp">3.9 [수상실적]</a></li>
			<li><a href="./동아리/동아리.jsp">3.6 [동아리 참여학생 비율]</a></li>
		</ul>
	
	<%
		} else if (id.equals("취업창업지원과")) {
	%>
	
		<ul class="depth_1">
			<li><a href="./취업/취업현황.jsp">1.3 [취업현황]</a></li>
			<li><a href="./취업자/취업자.jsp">1.3-1 [취업자]</a></li>
			<li><a href="./취업제외자/취업제외자.jsp">1.3-2 [취업제외자]</a></li>
			<li><a href="./현장실습/현장실습.jsp">3.3 [현장실습 이수학생]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./동아리/동아리.jsp" onclick="send_account()">3.6 [동아리 참여학생 비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
		</ul>
	 
	<%
		} else if (id.equals("학생과")) {
	%>
		<ul class="depth_1">
			<li><a href="./장학금/교외장학금.jsp">2.2 [재학생1인당 교외장학금]</a></li>
			<li><a href="./봉사실적/봉사실적.jsp">3.8 [학생 봉사 실적]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
	
	<%
		} else if (id.equals("창업보육센터")) {
	%>
	
		<ul class="depth_1">
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./동아리/동아리.jsp" onclick="send_account()">3.6 [동아리 참여학생 비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
	
	<%
		} else if (id.equals("admin")) {
	%>
		
		관리자용
		<ul class="depth_1">
			<li><a href="./입력확인/입력확인.jsp">입력확인</a></li>
			<li><a href="" onclick="changeState()">권한변경</a></li>
			<li><a href="./학과현황/학과현황.jsp">학과현황</a></li>
			<li><a href="./교원현황/교원현황.jsp">교원현황</a></li>
			<li><a href="./재학생현황2/재학생현황2.jsp">10.1일자 기준 재학생 현황</a></li>
		</ul>
		
		A그룹
		<ul class="depth_1">
			<li><a href="./재학생현황/재학생현황.jsp">1.1 [재학생 충원율]</a></li>
			<li><a href="./신입생현황/신입생현황.jsp">1.2 [정원내 신입생 충원율]</a></li>
			<li><a href="./취업/취업현황.jsp">1.3 [취업현황]</a></li>
			<li><a href="./취업자/취업자.jsp">1.3-1 [취업자]</a></li>
			<li><a href="./취업제외자/취업제외자.jsp">1.3-2 [취업제외자]</a></li>
			<li><a href="./연구실적/연구실적.jsp">1.4 [전임교원 1인당 연구실적]</a></li>
			<li><a href="./연구비/연구비.jsp">1.5 [전임교원 1인당 교외연구비]</a></li>
			<li><a href="./전임교원1인당국책사업수주실적/전임교원1인당국책사업수주실적.jsp">1.6 [국책사업수주실적]</a></li>
			<li><a href="./강의담당/강의담당.jsp">1.7 [전임 교원 강의담당 비율]</a></li>
			<li><a href="./강의담당상세/강의담당상세.jsp">1.7-1 [전임 교원 강의담당 비율 상세자료]</a></li>
		</ul>
	
		B그룹
		<ul class="depth_1">
			<li><a href="./중도탈락률/중도탈락률.jsp">2.1 [중도 탈락률]</a></li>
			<li><a href="./장학금/교외장학금.jsp">2.2 [재학생1인당 교외장학금]</a></li>
			<li><a href="./발전기금/발전기금조성액.jsp">2.3 [발전기금 조성액]</a></li>
			<li><a href="./발전기금상세/발전기금조성액상세.jsp">2.3-1 [발전기금 조성액 상세자료]</a></li>
			<li><a href="./전임교원/전임교원확보율.jsp">2.4 [전임교원확보율]</a></li>
		</ul>
	
		C그룹
		<ul class="depth_1">
			<li><a href="./외국인학생비율/외국인학생비율.jsp">3.1 [외국인학생비율]</a></li>
			<li><a href="./설문조사/설문조사.jsp">3.2 [학생 만족도 평가]</a></li>
			<li><a href="./현장실습/현장실습.jsp">3.3 [현장실습 이수학생]</a></li>
			<li><a href="./캡스톤디자인/캡스톤디자인.jsp">3.4 [캡스톤 디자인 이수학생 비율]</a></li>
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./동아리/동아리.jsp">3.6 [동아리 참여학생 비율]</a></li>
			<li><a href="./특허등록/특허등록.jsp">3.7 [특허등록및 기술이전 수입료]</a></li>
			<li><a href="./특허등록상세/특허등록상세.jsp">3.7-1 [상세자료(특허등록)]</a></li>
			<li><a href="./기술이전/기술이전.jsp">3.7-2 [상세자료(기술이전)]</a></li>
			<li><a href="./봉사실적/봉사실적.jsp">3.8 [학생 봉사 실적]</a></li>
			<li><a href="./수상실적/수상실적상세자료.jsp">3.9 [수상실적]</a></li>
			<li><a href="./강의공개실적/강의공개실적.jsp">3.10 [강의공개 실적]</a></li>
		</ul>
	
		외부지표
		<ul class="depth_1">
			<li><a href="./신입생현황외부/신입생현황외부.jsp">외부지표_신입생</a></li>
			<li><a href="./중도탈락률외부/중도탈락률외부.jsp">외부지표_중도탈락률</a></li>
			<li><a href="./취업자외부/취업자외부.jsp">외부지표_취업자</a></li>
			<li><a href="./취업제외자외부/취업제외자외부.jsp">외부지표_취업제외자</a></li>
			<li><a href="./취업외부/취업현황외부.jsp">외부지표_취업현황</a></li>
			<li><a href="./연구실적외부/연구실적외부.jsp">외부지표_연구실적</a></li>
			<li><a href="./연구비외부/연구비외부.jsp">외부지표_연구비</a></li>
			<li><a href="./강의담당외부/강의담당외부.jsp">외부지표_강의담당</a></li>
			<li><a href="./강의담당상세외부/강의담당상세외부.jsp">외부지표_강의담당</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
		
	<%
		} else if (id.equals("기타")) {
	%>
	
		<ul class="depth_1">
			<li><a href="./교육프로그램참여비율/교육프로그램참여비율.jsp">3.5 [교육프로그램 참여비율]</a></li>
			<li><a href="./최종결과/A그룹.jsp">A그룹</a></li>
			<li><a href="./최종결과/B그룹.jsp">B그룹</a></li>
			<li><a href="./최종결과/C그룹.jsp">C그룹</a></li>
			<li><a href="./최종결과/D그룹.jsp">D그룹</a></li>
		</ul>
	
	<%
		} else {
	%>
	<script>
		alert("아이디와 비밀번호를 확인해주세요");
	</script>
	<%
		}
	%>
	</font>
</div>
<!-- //menu -->