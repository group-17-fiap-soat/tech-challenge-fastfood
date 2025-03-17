package tech.challenge.fastfood.fastfood.adapters.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("health-check")
class HealthCheckController {

    @GetMapping
    fun healthCheck(): String {
        //TODO fazer health check com enum no futuro
        val message = "Healthy 🥗"

        val htmlTemplate = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Health Check</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f0f8ff;
                        color: #333;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                    }
                    .container {
                        text-align: center;
                        background-color: #32cd32;
                        padding: 50px;
                        border-radius: 10px;
                        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    }
                    .status {
                        font-size: 2rem;
                        font-weight: bold;
                    }
                    .emoji {
                        font-size: 3rem;
                    }
                </style>
            </head>
            <body>
            <div class="container">
                <div class="status">
                    <span id="message">Health Check: {{message}}</span>
                </div>
            </div>
            </body>
            </html>
        """
            .trimIndent()

        return htmlTemplate.replace("{{message}}", message)
    }
}