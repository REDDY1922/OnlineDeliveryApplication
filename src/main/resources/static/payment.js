var stripe = Stripe('your-public-stripe-key'); // Replace with your Stripe public key
var elements = stripe.elements();
var card = elements.create('card');
card.mount('#card-element');

var form = document.getElementById('payment-form');
form.addEventListener('submit', function(event) {
    event.preventDefault();

    stripe.createToken(card).then(function(result) {
        if (result.error) {
            // Show error in #payment-result div
            document.getElementById('payment-result').textContent = result.error.message;
        } else {
            // Send the token to the server
            fetch('/api/payments/charge', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    'token': result.token.id,
                    'amount': '5000', // Amount in cents (e.g., $50)
                    'currency': 'usd',
                    'customerEmail': 'customer@example.com' // Replace with dynamic customer email
                }),
            }).then(function(response) {
                return response.text();
            }).then(function(responseText) {
                document.getElementById('payment-result').textContent = responseText;
            });
        }
    });
});