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
    console.log(resultId)
}

function goTestInfo(testId) {
    console.log(testId)
}