document.getElementById('attachBtn').addEventListener('click', function() {
    document.getElementById('fileInput').click();
});

document.getElementById('fileInput').addEventListener('change', function() {
    const files = this.files;
    const fileNamesContainer = document.getElementById('fileNames');
    const uploadedFiles = fileNamesContainer.getElementsByClassName('thumbnail-container').length;
    const remainingSlots = 10 - uploadedFiles;
    
    if (files.length > remainingSlots) {
        alert('최대 10개의 파일만 업로드할 수 있습니다.');
        return;
    }

    if (files.length > 0) {
        for (let i = 0; i < files.length; i++) {
            const file = files[i];
            const fileName = file.name;

            const thumbnailContainer = document.createElement('div');
            thumbnailContainer.classList.add('thumbnail-container');

            const thumbnail = document.createElement('img');
            thumbnail.src = URL.createObjectURL(file);
            thumbnail.alt = file.name;
            thumbnail.classList.add('thumbnail');
            thumbnailContainer.appendChild(thumbnail);

            const fileNameText = document.createElement('div');
            fileNameText.textContent = fileName;
            thumbnailContainer.appendChild(fileNameText);

            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'x';
            deleteButton.classList.add('delete-button');
            deleteButton.addEventListener('click', function() {
                thumbnailContainer.remove();
                updateAttachedFilesCount(); // 파일 삭제 시 카운트 업데이트
            });
            thumbnailContainer.appendChild(deleteButton);

            fileNamesContainer.appendChild(thumbnailContainer);
        }
    }
    updateAttachedFilesCount();
});

function updateAttachedFilesCount() {
    const fileNamesContainer = document.getElementById('fileNames');
    const uploadedFiles = fileNamesContainer.getElementsByClassName('thumbnail-container').length;
    const remainingSlots = 10 - uploadedFiles;
    const attachBtn = document.getElementById('attachBtn');
    attachBtn.textContent = `+ 파일 첨부하기 (${uploadedFiles}/10)`;
}
		
		/*
	    document.getElementById('submitBtn').addEventListener('click', function() {
	        document.getElementById('postForm').submit();
	    });
	    */

// 페이지 로드 시 초기 파일 개수 설정
updateAttachedFilesCount();