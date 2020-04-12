<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="col-md-2">
        <ul class="nav nav-pills flex-column">
            <br>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath()%>/admin/adminMain">메인페이지</a><br>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath()%>/admin/adminMember">회원관리</a><br>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath()%>/admin/adminPayment">결재관리</a><br>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath()%>/admin/support">고객지원관리</a><br>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath()%>/admin/boardManage">신고게시판관리</a><br>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath()%>/admin/adminSetting">사이트설정</a>
            </li>
        </ul>
</div>