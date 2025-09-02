document.addEventListener('DOMContentLoaded', () => {
    const longUrlInput = document.getElementById('longUrlInput');
    const shortenButton = document.getElementById('shortenButton');
    const resultSection = document.getElementById('resultSection');
    const shortenedUrlOutput = document.getElementById('shortenedUrlOutput');
    const copyButton = document.getElementById('copyButton');
    const errorMessage = document.getElementById('errorMessage');

    shortenButton.addEventListener('click', async () => {
        const originalUrl = longUrlInput.value.trim();

        if (!originalUrl) {
            showError("Por favor, introduce una URL válida.");
            return;
        }

        try {
            const response = await fetch('/api/v1/shorten', {
                method: 'POST',
                headers: {
                    'Content-Type': 'text/plain' // La API espera texto plano
                },
                body: originalUrl
            });

            if (!response.ok) {
                throw new Error('Error al acortar la URL');
            }

            const shortUrl = await response.text();
            shortenedUrlOutput.value = shortUrl;
            resultSection.classList.remove('hidden');
            errorMessage.classList.add('hidden'); // Ocultar errores si hubo
        } catch (error) {
            console.error('Error:', error);
            showError("No se pudo acortar la URL. Revisa que sea válida.");
            resultSection.classList.add('hidden'); // Ocultar resultado si hubo error
        }
    });

    copyButton.addEventListener('click', () => {
        shortenedUrlOutput.select();
        shortenedUrlOutput.setSelectionRange(0, 99999); // Para móviles
        document.execCommand('copy');
        alert('¡URL corta copiada al portapapeles!');
    });

    function showError(message) {
        errorMessage.textContent = message;
        errorMessage.classList.remove('hidden');
    }
});