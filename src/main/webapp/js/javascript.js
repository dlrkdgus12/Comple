//"use strict";
//window.onload = function(){
//	$("signupLink").onclick = signupLinkClick;
//	$("loginLink").onclick = loginLinkClick;
//};

// function enterKeyPressed(event) {
//     if (event.KeyCode == 13){
//         login();
//     }
// }

//submit button 눌렀을 때 login 시작

// function emailCheck(){
// 	String pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
// 	if(pattern.test($("inputName").value)==false){

// 	} 
// }

// function passwordCheck(){

// }

function loadAJAX(url, parameter, sucfunc, errorfunc, indicator) {
	// 로딩 인디케이터
	
	if (indicator == true) {
		$('#loading').show();
		// var loading = $('<div class="loading"
		// style="position:fixed;margin:auto;z-index:100;"><img alt="loading"
		// src="/admin/css/images/ajax-loader.gif"/></div>').appendTo(document.body);
	}
	// 테이블 리스트 로딩
	$.ajax({
		type : "POST" // "POST", "GET"
		,
		async : true // true, false
		,
		url : url // Request URL
		,
		dataType : "json" // 전송받을 데이터의 타입
		// "xml", "html", "script", "json" 등 지정 가능
		// 미지정시 자동 판단
		,
		timeout : 30000 // 제한시간 지정
		,
		cache : false // true, false
		,
		data : parameter
		// $("#inputForm").serialize() //서버에 보낼 파라메터
		// form에 serialize() 실행시 a=b&c=d 형태로 생성되며 한글은 UTF-8 방식으로 인코딩
		// "a=b&c=d" 문자열로 직접 입력 가능
		// {a:b, c:d} json 형식 입력 가능
		,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		error : function(request, status, error) {
			// 통신 에러 발생시 처리
			if (errorfunc != null && !(errorfunc === undefined)) {
				errorfunc(request, status, error);
			} else {
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText);
			}
		},
		success : function(response, status, request) {
			// 통신 성공시 처리
			if (response.status == 13) {
				alert("session out\n다시 로그인해 주세요");
				window.location.reload();
			} else {
				sucfunc(request, status, response);
			}
		},
		beforeSend : function(xhr) {
			// 통신을 시작할때 처리
			xhr.setRequestHeader("AJAX", true);
		},
		complete : function() {
			// 통신이 완료된 후 처리
			$('#loading').hide();
		}
	});

	// function handleHttpError(request, status, error){
	// alert("code : " + request.status + "\r\nmessage : " +
	// request.reponseText);
	// }
}

function loadAJAXforGET(url, parameter, sucfunc, errorfunc, indicator) {
	// 로딩 인디케이터
	if (indicator == true) {
		// var loading = $('<div class="loading"
		// style="position:fixed;margin:auto;z-index:100;"><img alt="loading"
		// src="/admin/css/images/ajax-loader.gif"/></div>').appendTo(document.body);
	}

	// 테이블 리스트 로딩
	$.ajax({
		type : "GET" // "POST", "GET"
		,
		async : true // true, false
		,
		url : url // Request URL
		,
		dataType : "json" // 전송받을 데이터의 타입
		// "xml", "html", "script", "json" 등 지정 가능
		// 미지정시 자동 판단
		,
		timeout : 30000 // 제한시간 지정
		,
		cache : false // true, false
		,
		data : parameter
		// $("#inputForm").serialize() //서버에 보낼 파라메터
		// form에 serialize() 실행시 a=b&c=d 형태로 생성되며 한글은 UTF-8 방식으로 인코딩
		// "a=b&c=d" 문자열로 직접 입력 가능
		// {a:b, c:d} json 형식 입력 가능
		,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		error : function(request, status, error) {
			// 통신 에러 발생시 처리
			// alert("code : " + request.status + "\r\nmessage : " +
			// request.reponseText);
			if (errorfunc != null && !(errorfunc === undefined)) {
				errorfunc(request, status, error);
			} else {
				if (request.status != 0) {
					alert("code11 : " + request.status + "\r\nmessage : "
							+ request.reponseText);
				}
			}

		},
		success : function(response, status, request) {
			// 통신 성공시 처리
			if (response.status == -13) {
				alert("session out\n다시 로그인해 주세요");
				window.location.reload();
			} else {
				sucfunc(request, status, response);
			}
		},
		beforeSend : function(xhr) {
			// 통신을 시작할때 처리
			xhr.setRequestHeader("AJAX", true);
			if (indicator == true) {
				// $('.wrap-loading').removeClass('display-none');
			}
		},
		complete : function() {
			// 통신이 완료된 후 처리
			// $('#loading').hide();
			if (indicator == true) {
				// $('.wrap-loading').addClass('display-none');
			}
		}
	});
}
// 페이지 번호 그리는 함수
function pageController(divname, curPage, pageRow, total, callback) {
	var html = "";
	var maxPage = Math.ceil(total / pageRow);
	if (maxPage == 0)
		maxPage = 1;
	html += "<div class='st_pagination'><ul class='pagination'>";

	// 이전 페이지가 있으면
	if (curPage != 1) {
		if (callback == null || callback === undefined || callback == "") {
			html += '<li class="active"><a href="javascript:searchContent('
					+ (curPage - 1) + ');">&laquo;</a></li>';
		} else {
			html += '<li class="active"><a href="javascript:' + callback + '('
					+ (curPage - 1) + ');">&laquo;</a></li>';
		}
	} else {
		html += '<li class="disabled"><a href="#">&laquo;</a></li>';
	}

	// 중간 숫자 계산
	for ( var i = -2; i < 3; i++) {
		var index = curPage + i;
		if (index > 0 && index <= maxPage) {
			if (i == 0) {
				html += '<li class="disabled"><a href="#">' + index
						+ '</a></li>';
			} else {
				if (callback == null || callback === undefined
						|| callback == "") {
					html += '<li class="active"><a href="javascript:searchContent('
							+ index + ');">' + index + '</a></li>';
				} else {
					html += '<li class="active"><a href="javascript:'
							+ callback + '(' + index + ');">' + index
							+ '</a></li>';
				}
			}
		}
	}

	// 다음 페이지가 있으면
	if (curPage != maxPage) {
		if (callback == null || callback === undefined || callback == "") {
			html += '<li class="active"><a href="javascript:searchContent('
					+ (curPage + 1) + ');">&raquo;</a></li>';
		} else {
			html += '<li class="active"><a href="javascript:' + callback + '('
					+ (curPage + 1) + ');">&raquo;</a></li>';
		}
	} else {
		html += '<li class="disabled"><a href="#">&raquo;</a></li>';
	}
	html += '</ul></div>';

	$('#' + divname).html(html);
}

function transDate(input) {

	var myDate = new Date(input);
	var result = "";
	var month;
	var date;
	var hour;
	var minutes;
	var seconds;

	if (myDate.getMonth() < 9) {
		month = "0".concat((myDate.getMonth() + 1));
	} else {
		month = myDate.getMonth() + 1;
	}

	if (myDate.getDate() <= 9) {
		date = "0".concat(myDate.getDate());
	} else {
		date = myDate.getDate();
	}
	if (myDate.getHours() <= 9) {
		hour = "0".concat(myDate.getHours());
	} else {
		hour = myDate.getHours();
	}
	if (myDate.getMinutes() <= 9) {
		minutes = "0".concat(myDate.getMinutes());
	} else {
		minutes = myDate.getMinutes();
	}
	if (myDate.getSeconds() <= 9) {
		seconds = "0".concat(myDate.getSeconds());
	} else {
		seconds = myDate.getSeconds();
	}
	
	
	result += myDate.getFullYear() + "-" + month + "-" + date + " " +  hour + ":" + minutes + ":" + seconds;
	return result;
}
function convertPid(input) {
	var result = input.replace(/A/gi, "K");
	result = result.replace(/B/gi, "L");
	result = result.replace(/C/gi, "M");
	result = result.replace(/D/gi, "N");
	result = result.replace(/E/gi, "O");
	result = result.replace(/F/gi, "P");
	result = result.replace(/0/gi, "A");
	result = result.replace(/1/gi, "B");
	result = result.replace(/2/gi, "C");
	result = result.replace(/3/gi, "D");
	result = result.replace(/4/gi, "E");
	result = result.replace(/5/gi, "F");
	result = result.replace(/6/gi, "G");
	result = result.replace(/7/gi, "H");
	result = result.replace(/8/gi, "I");
	result = result.replace(/9/gi, "J");
	return result;
}
function convertSN(input) {
	var result = input.replace(/A/gi, "0");
	result = result.replace(/B/gi, "1");
	result = result.replace(/C/gi, "2");
	result = result.replace(/D/gi, "3");
	result = result.replace(/E/gi, "4");
	result = result.replace(/F/gi, "5");
	result = result.replace(/G/gi, "6");
	result = result.replace(/H/gi, "7");
	result = result.replace(/I/gi, "8");
	result = result.replace(/J/gi, "9");
	result = result.replace(/K/gi, "A");
	result = result.replace(/L/gi, "B");
	result = result.replace(/M/gi, "C");
	result = result.replace(/N/gi, "D");
	result = result.replace(/O/gi, "E");
	result = result.replace(/P/gi, "F");
	return result;
}
function own_statusToText(input){
	var result = "";
	if( input == "1")
		result = "정상";
	else if(input == "4")
		result = "일시정지";
	else if(input == "5")
		result = "친구초대후 일주일 경과";
	else if(input == "6")
		result = "기록 종료된 Planty";
	else if(input == "8")
		result = "초대한 친구 대기중";
	else if(input == "9")
		result = "주인의 친구 삭제";
	return result;
}
function statusToText(input)
{
	var result = "";
	if( input == "2")
		result = "시작 전";
	else if(input == "3")
		result = "시작 됨";
	else if(input == "4")
		result = "정보 입력 전";
	else if(input == "5")
		result = "일시정지";
	else if(input == "6")
		result = "기록 종료";
	else if(input == "7")
		result = "기록 종료 및 재시작 됨";
	else if(input == "8")
		result = "24시간 이상 데이터를 받지 못함";
	else if(input == "9")
		result = "삭제 됨";
	return result;
}
function water_statusToText(input){
	var result ="";
	if(input == "1"){
		result = "성공";
	}
	else if(input == "2"){
		result = "물통에 물이 부족";
	}
	else if(input == "3"){
		result = "습도가 충분함";
	}
	else if(input == "4"){
		result = "planty 응답없음";
	}
	else if(input == "10"){
		result = "기타";
	}
	else if(input == "701"){
		result = "서버 응답 대기중";
	}
	else if(input == "702"){
		result = "이전 요청 만료";
	}
	return result;
}
function plantySensorStatus(input){
	var result ="";
	result = input;
	if(input == true){
		result = "경고";
	}
	else if(input == false){
		result = "정상";
	}
	return result;
}
function toBooleanSensorStatus(input){
	var result = 0;
	if(input == "경고"){
		result = 1;
	}
	else if(input == "정상"){
		result = 0;
	}
	return Boolean(result);
}
function textToStatus(input)
{
	var result = input;
	if( input == "시작 전")
		result = "2";
	else if(input == "시작 됨")
		result = "3";
	else if(input == "정보 입력 전")
		result = "4";
	else if(input == "일시정지")
		result = "5";
	else if(input == "기록 종료")
		result = "6";
	else if(input == "기록 종료 및 재시작 됨")
		result = "7";
	else if(input == "24시간 이상 데이터를 받지 못함")
		result = "8";
	else if(input == "삭제 됨")
		result = "9";
	return result;
}
$(document).ajaxStart(function() {
	$('.sk-circle').show();
	}).ajaxStop(function() {
	$('.sk-circle').hide();
	});