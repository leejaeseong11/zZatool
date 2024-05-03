function reload(url, memberId) {
    location.href = url + '/' + memberId;
}

function goResult(url, memberId, page) {
    location.href = url + '/' + memberId + '/result/' + page;
}

function goTest(url, memberId, order, page) {
    location.href = url + '/' + memberId + '/test/' + order + '/' + page;
}

function goResultInfo(resultId) {
    location.href = '/result/' + resultId;
}

function goTestInfo(testId) {
    location.href = '/test/' + testId;
}