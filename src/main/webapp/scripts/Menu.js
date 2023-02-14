function handleOpenMenu(){
	const menu = document.getElementById("menu");
	const arrow = document.getElementById("arrow");
	
	const isMenuVisible = menu.classList.contains("hidden");
	
	console.log(isMenuVisible);
	
	if(isMenuVisible){
		menu.classList.remove("hidden");
		arrow.innerHTML = "arrow_drop_up";
		return;
	}
	
	menu.classList.add("hidden");
	arrow.innerHTML = "arrow_drop_down";
}