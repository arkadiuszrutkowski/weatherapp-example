# Weather App

This repository demonstrate minimalistic clean architecture including MVP pattern and Dagger 2 as IoC container. It servers only for educational purposes.

# Prerequites

The project uses OpenWeatherMap API (http://openweathermap.org/) for REST calls. Due to their restrictions the API calls should be invoked with API key. Key could be obtained with simple signing up on their site (don't worry, it's completely free!) and it should be available in user's profile. My project won't be working without key because it's stored in local.properties. When user already has a key then just put into local.properties this line - weatherApiKey=YOUR_API_KEY. It's not necessary to use quotation marks inside YOUR_API_KEY.
