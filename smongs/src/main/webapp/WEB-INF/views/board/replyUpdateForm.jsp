<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<!--/* 댓글 수정 popup */-->
    <div id="replyUpdatePopup" class="popLayer">
        <h3>댓글 수정</h3>
        <div class="pop_container">
            <table class="tb tb_row tl">
                <colgroup>
                    <col style="width:30%;" /><col style="width:70%;" />
                </colgroup>
                <tbody>
                    <tr>
                        <th scope="row">작성자</th>
                        <td><input type="text" id="modalWriter" name="modalWriter" readonly/>${reply.user.username}</td>
                    </tr>
                    <tr>
                        <th scope="row">내용</th>
                        <td><textarea id="modalContent" name="modalContent" cols="90" rows="10" placeholder="수정할 내용을 입력해 주세요.">${reply.content}</textarea></td>
                    </tr>
                </tbody>
            </table>
            <p class="btn_set">
                <button type="button" id="replyUpdateBtn" class="btns btn_st2">수정</button>
                <button type="button" class="btns btn_bdr2" onclick="closeReplyUpdatePopup();">취소</button>
            </p>
        </div>
        <button type="button" class="btn_close" onclick="closeReplyUpdatePopup();"><span><i class="far fa-times-circle"></i></span></button>
    </div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>