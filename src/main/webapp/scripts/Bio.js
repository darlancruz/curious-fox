function handleEditBio(){
	event.preventDefault();
	const bio = document.getElementById("bio");
	const newBio = document.getElementById("new_bio");
	const bioButtons = document.getElementById("bio_buttons");
	
	if(bio.classList.contains("hidden")){
		newBio.classList.add("hidden");
		bioButtons.classList.add("hidden");
		bio.classList.remove("hidden");
		return;
	}
	
	bio.classList.add("hidden");
	newBio.classList.remove("hidden");
	bioButtons.classList.remove("hidden");	
}