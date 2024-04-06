function reload(url, memberId) {
    console.log(url+", "+memberId);
    location.href = url + '/' + memberId;
}

function goResult(url, memberId) {
    console.log(url+", "+memberId);
    location.href = url + '/' + memberId + '/result/1';
}

function goTest(url, memberId) {
    console.log(url+", "+memberId);
    location.href = url + '/' + memberId + '/test/date/1';
}