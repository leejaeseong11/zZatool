function setHref(url, changedQuery, value) {
    url = url.slice(8);
    let queryList = url.split("&");
    queryList = queryList.map(query => {
        if (query.split("=")[0] === changedQuery) {
            return changedQuery + "=" + value;
        }
        return query;
    });
    location.href = '/search?' + queryList.join("&");
}

function enterKey(url, changedQuery, value) {
    if(window.event.keyCode == 13) {
        setHref(url, changedQuery, value);
    }
}