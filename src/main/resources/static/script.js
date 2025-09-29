document.getElementById('urlForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const originalUrl = document.getElementById('originalUrl').value;
    const resultContainer = document.getElementById('resultSection');
    const shortUrlLink = document.getElementById('shortUrl');
    const errorMessage = document.getElementById('errorMessage');

    console.log(originalUrl);

    resultContainer.style.display = 'none';
    errorMessage.style.display = 'none';

    try {
        const response = await fetch('http://localhost:8080/shorten', {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: originalUrl
        });

        if (response.ok) {
            const shortUrl = await response.text();
            shortUrlLink.href = shortUrl;
            shortUrlLink.textContent = shortUrl;
            resultContainer.style.display = 'block';
        } else {
            errorMessage.style.display = 'block';
        }
    } catch (error) {
        errorMessage.style.display = 'block';
    }
});