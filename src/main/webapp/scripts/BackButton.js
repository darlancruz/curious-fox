function handleBackButton(){
	const history = sessionStorage.getItem("history");
	const historyArr = JSON.parse(history);
	
	if(history && historyArr.length > 0){
		window.location.replace(historyArr[historyArr.length -1]);
		historyArr.pop();
		const stringifiedHistory = JSON.stringify(historyArr);
		sessionStorage.setItem("history", stringifiedHistory);
		return;
	}
	
	window.location.replace("/curious-fox");
}


function addToHistory(){
	const url = window.location.href;
	const history = sessionStorage.getItem("history");
	
	if(history){
		const historyArr = JSON.parse(history);
		historyArr.push(url);
		
		const stringifiedHistory = JSON.stringify(historyArr);
		sessionStorage.setItem("history", stringifiedHistory);
		return;
	}
	
	const historyArr = [];
	historyArr.push(url);
	
	const stringifiedHistory = JSON.stringify(historyArr);
	sessionStorage.setItem("history", stringifiedHistory);
}
