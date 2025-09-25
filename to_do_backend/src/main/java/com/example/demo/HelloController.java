package com.example.todobackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Basic utility endpoints and a themed welcome for the To-Do backend.
 * Uses the Ocean Professional theme colors for inline HTML preview.
 */
@RestController
@Tag(name = "Utility", description = "Basic health and documentation endpoints")
public class HelloController {

    @GetMapping("/")
    @Operation(summary = "Welcome", description = "Ocean Professional themed welcome message.")
    public String hello() {
        // Ocean Professional palette
        String primary = "#2563EB";  // blue
        String secondary = "#F59E0B"; // amber
        String background = "#f9fafb";
        String surface = "#ffffff";
        String text = "#111827";

        return """
            <html>
              <head>
                <title>To-Do Backend • Ocean Professional</title>
                <style>
                  body { font-family: system-ui, -apple-system, Segoe UI, Roboto, sans-serif; background:%s; color:%s; margin:0; }
                  .card { max-width: 720px; margin:48px auto; background:%s; border-radius:14px; padding:28px 28px; 
                          box-shadow: 0 10px 25px rgba(37,99,235,0.10), 0 4px 12px rgba(17,24,39,0.06); 
                          border: 1px solid rgba(17,24,39,0.05); }
                  .title { font-weight:700; font-size:24px; color:%s; margin:0 0 8px; letter-spacing: -0.01em; }
                  .subtitle { color:#374151; margin:0 0 18px; }
                  .bar { height:6px; width:120px; background:linear-gradient(90deg, %s, %s); border-radius: 6px; margin: 12px 0 18px; }
                  .links a { text-decoration:none; color:white; background:%s; padding:10px 14px; border-radius:10px; margin-right:10px; 
                             transition: transform .12s ease, box-shadow .12s ease, background .2s ease; display:inline-block; }
                  .links a.secondary { background:%s; color:#111827; }
                  .links a:hover { transform: translateY(-1px); box-shadow: 0 6px 14px rgba(37,99,235,0.25); }
                  .meta { margin-top:16px; font-size:13px; color:#6B7280; }
                </style>
              </head>
              <body>
                <div class="card">
                  <div class="title">To-Do Backend API</div>
                  <div class="bar"></div>
                  <p class="subtitle">Modern REST API with Ocean Professional theme. Manage to-do items with clean, predictable endpoints.</p>
                  <div class="links">
                    <a href="/swagger-ui.html">OpenAPI Docs</a>
                    <a class="secondary" href="/api/info">API Info</a>
                  </div>
                  <div class="meta">Primary %s • Secondary %s</div>
                </div>
              </body>
            </html>
            """.formatted(background, text, surface, primary, primary, secondary, primary, secondary, primary, secondary);
    }

    @GetMapping("/docs")
    @Operation(summary = "API Documentation", description = "Redirects to Swagger UI")
    public RedirectView docs() {
        return new RedirectView("/swagger-ui.html");
    }

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status")
    public String health() {
        return "OK";
    }

    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information")
    public String info() {
        return "Spring Boot Application: todobackend (Ocean Professional)";
    }
}