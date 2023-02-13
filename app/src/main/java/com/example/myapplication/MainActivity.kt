package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val tokens = listOf(
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNWI0MDljNmYyMmM0MDNlMWY5MWY5ODY3YWM0OTJhOTA2MTk1NTgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiWmVlc2hhbiBSYXNvb2wiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUVkRlRwNmpYWlhDNWpMM3BPUnJmOHNsYVNJSzJxYzJwdGtXZHZXX2NUdTY9czk2LWMiLCJmYW1pbHlfaWQiOiIwMUdRQ0tLTThLSFZCQzg1R1cwV1cyOUhBRiIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9mYW1pbHlrZWVwZXItbWFpbiIsImF1ZCI6ImZhbWlseWtlZXBlci1tYWluIiwiYXV0aF90aW1lIjoxNjc0NTY2NTU3LCJ1c2VyX2lkIjoialVLVmZzbDFtUmZYSUNxQ0VNWXJHckVQUmU5MyIsInN1YiI6ImpVS1Zmc2wxbVJmWElDcUNFTVlyR3JFUFJlOTMiLCJpYXQiOjE2NzUxNTkyODIsImV4cCI6MTY3NTE2Mjg4MiwiZW1haWwiOiJ6ZWVzaGFuLnJhc29vbEByZWFzb25sYWJzLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA1NDczNDQ3ODgwNzU2MDU2Nzk1Il0sImVtYWlsIjpbInplZXNoYW4ucmFzb29sQHJlYXNvbmxhYnMuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.OV0G6rUjZDRznwO7VqOJn8v9I5eMYtBYwrYLm5KDnioDARnDt9HgFc07N5Z0iZPqFkgLMLnEgyn9VUxZrnuvuj87Ec-5-qOC4HzLXL3ekqmFMtr3vwX6aAq9kfLOk3zJtUYxhgQEbrf563QpEWrdIKI3bVvVnGSu8PQlO8oKA_55jQYZ1w3PyCEyuBs90hdC03uabBSWCQflVi5bFeQYEhOiEF1aSs1DjQGUWmlHLRo2rlUOigO6VNRgRP9chWrIiBfwaqgk_LJtZ9_Xk8dKeZCVSkE2FmBwFWqYXrM65xz6JqyHbgcBzEMA978ZptCSAPIX8Z0iCw6z8dML9YaOUA",
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNWI0MDljNmYyMmM0MDNlMWY5MWY5ODY3YWM0OTJhOTA2MTk1NTgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoi2YbYp9mH24zYryDYp9mF24zZhiDYp9io2KfYr9uMIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FFZEZUcDZCUmx4dlJpMVo1TUhfeWhfdENUQUY2R3JIRF9rcHdsaU0teEoyPXM5Ni1jIiwiZmFtaWx5X2lkIjoiMDFHUVRHTkJLMzdSSDlOREIyODJKWEhaUVEiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vZmFtaWx5a2VlcGVyLW1haW4iLCJhdWQiOiJmYW1pbHlrZWVwZXItbWFpbiIsImF1dGhfdGltZSI6MTY3NDg1MzM4OSwidXNlcl9pZCI6IndBUGZPelN3eWNieXFlT2N0NlZpNGdQWHlPbTIiLCJzdWIiOiJ3QVBmT3pTd3ljYnlxZU9jdDZWaTRnUFh5T20yIiwiaWF0IjoxNjc0ODUzMzk3LCJleHAiOjE2NzQ4NTY5OTcsImVtYWlsIjoibmFoaWRhbWluYWJhZGkxMzcwQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA0MzY5OTc2NTI0Njc4NTcxMDE5Il0sImVtYWlsIjpbIm5haGlkYW1pbmFiYWRpMTM3MEBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.ho_QafkvQzQJ0UgRI7iOkQaKpPeZeJosvBKoOvri88_DuUPSFfFzwNSTmwSB4VnClMwZvoI5TtufIzdY8uACYqWNtkymmCsIZFY8koic3vzsdj96NTkm8mvKTEpBXaVijxIxeO9FxHhek9kxtFexXhO6JLx2h1n5bLWtDivrFMogqUYFwgcHMpM4CxDzgCAjWT-fxYoS6ka3WlKvn15Y9Kiw_b4dA5jcTgUCSdcK_KrtaOFKM5ZepHFpPwLzjFchGsuCPVIzXLmYi9RA8Xhnkro52J96pcw9ocjd0EfxEJgF-wBuL_b_6aYRHua7i5d2XIBfp1uCj1cJlNHTUk0aXQ",
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNWI0MDljNmYyMmM0MDNlMWY5MWY5ODY3YWM0OTJhOTA2MTk1NTgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoi2YXYrdmF2K8g2LHYttinINqG2LHYp9i624wiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUVkRlRwNS16TU9pWmtJN3RVMmxyWGxLV0tKcHFDMjNydGFpZHYwSS16MWY9czk2LWMiLCJmYW1pbHlfaWQiOiIwMUdRWkVFVEpBUlIyU1hXWFBHUzVXTkJRNCIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9mYW1pbHlrZWVwZXItbWFpbiIsImF1ZCI6ImZhbWlseWtlZXBlci1tYWluIiwiYXV0aF90aW1lIjoxNjc1MDE4ODUwLCJ1c2VyX2lkIjoieEJEM3FnQVE5SWFqU3YzeHVKS00wdUtHQXdhMiIsInN1YiI6InhCRDNxZ0FROUlhalN2M3h1SktNMHVLR0F3YTIiLCJpYXQiOjE2NzUwMTg4NTgsImV4cCI6MTY3NTAyMjQ1OCwiZW1haWwiOiJtaG1kcmRhY2hyYWdoeTc2OUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJnb29nbGUuY29tIjpbIjEwMjQ3MjQzNzczMjYwMzA2Nzg2MyJdLCJlbWFpbCI6WyJtaG1kcmRhY2hyYWdoeTc2OUBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.rVnhu9UJvWINro3kAk5RJ926Kox7GZpxRa-zmO5lWHpYaISH8g4uTMc6VrDoF93js36olXKKNAjTt4WN7IJOwClw-cnWC5GiXphFuD7fSIbqdvFBiT0J7rWxMXLPeJjYyhUC8th8XD5TnfAjeCEI7d2m7mQphIuseLH2_UpUQtkrLFJhx3ozIZ0NV_arXQUeJaEmg8wOcdoyLR12ZPo0w1xtZEZbRIu1Hm7MNOTsJZtCz_cLN1D52oZzgzyhSGHQNa0zOaIIB9sHLI2WOpGHXTRWBcHPMhvq512TCRzTTXqBszwbgj3nYWfOZ0x_xWFF5WbhN-MyRcdSPfBau5faTg",
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNWI0MDljNmYyMmM0MDNlMWY5MWY5ODY3YWM0OTJhOTA2MTk1NTgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoi2LnZhNuM2LHYttinINmF2K3Zhdiv2LLYp9iv2YciLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUVkRlRwNXBiUVlsMjlHZTYzdEFxUnFqTzBVaVVvTFF2TW1mZnlSa1JXMDI9czk2LWMiLCJmYW1pbHlfaWQiOiIwMUdRUVk5OFhDQzdCTk4xOTJSMTIwNlIzRyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9mYW1pbHlrZWVwZXItbWFpbiIsImF1ZCI6ImZhbWlseWtlZXBlci1tYWluIiwiYXV0aF90aW1lIjoxNjc0NzY3MDEzLCJ1c2VyX2lkIjoiM3NZcEJsQnNjUmhhZm5BSExwT3JmbkQweTNyMiIsInN1YiI6IjNzWXBCbEJzY1JoYWZuQUhMcE9yZm5EMHkzcjIiLCJpYXQiOjE2NzQ3NjcwMTcsImV4cCI6MTY3NDc3MDYxNywiZW1haWwiOiJtaG1kemFkaGx5cmRhOTdAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDYwMTYwNDc5MTMyOTE0NTkxODQiXSwiZW1haWwiOlsibWhtZHphZGhseXJkYTk3QGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.PeP33Zb-ynaYq8_DBWG-8s-1VXsepOrSjJw3sRxhwwsE1kUKyKy6KFGTeDlUwf5ETeT9dwAnZmwJGY_w5HGd-SvIjbL1W4gez7MhBa5T3ckG-Qc6lu6N6QNDu2nXXT4N1K-fN7tOCTj8Kus_S7aectQEq2CMq5jw0VGr63YtayV91Dsvy9GUdplMbOqEIIyanmpDs9YSjg3nUaoxZvB1dmko3YJ_ootvgA039GMl40eqg66_8OLXw0zLAkJW8XBManjK00TIeBn9aUUJVpFllVWAlVtIUtB13JsogmVDAYPbsHjeZyEj8_dIB5GPsUl0J5nyXdVU4Z6DQG2zNwcoiA",
           "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNTU5YzU5MDgzZDc3YWI2NDUxOThiNTIxZmM4ZmVmZmVlZmJkNjIiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoi2YXYrdmF2K8g2YXYrdmF2K8iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUVkRlRwNnQxajdJVk15WnRpLTh2Q1ExMTNBVS1PNWFybGFjMC1LMVU5UUY9czk2LWMiLCJmYW1pbHlfaWQiOiIwMUdRRjhZWlRDV0RGWFdIUzFSNTJaRUtIUSIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9mYW1pbHlrZWVwZXItbWFpbiIsImF1ZCI6ImZhbWlseWtlZXBlci1tYWluIiwiYXV0aF90aW1lIjoxNjc0NDc2MjE4LCJ1c2VyX2lkIjoibXJycjRZS2FJbFhmTHFMS1NGYVQyeEI2bUxtMSIsInN1YiI6Im1ycnI0WUthSWxYZkxxTEtTRmFUMnhCNm1MbTEiLCJpYXQiOjE2NzQ0NzYyNTYsImV4cCI6MTY3NDQ3OTg1NiwiZW1haWwiOiJnZmhlNTYwQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTE2MDQ1MDkwNTUyNzQwMTgzMjgyIl0sImVtYWlsIjpbImdmaGU1NjBAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoiZ29vZ2xlLmNvbSJ9fQ.cWqDmFQK_D1tbuwRZLffclHnSlfmFhi5-Z-S_jsp_dYdwtFOFtv1mMfbvjLLJNQArThjSs8YzTmDMIefsszLnKynGzWRfMyRrqahpO3py9zwQlTsyKDkVibahfu54huLHMgcYIIjcPpEKYgoiGSDU5izuS7cHTbKLDdD4mkqXmrDEV_cWg1kHuAwud7aX7WEToA086PCFj_K_5jj3ZI4KD2Cjr0xJTa9aS6ssCS5YqbiA5d2ep6lyrT8xKt_3mfx4rRB8IgIJVFYtTN6lHOA1xAXeoMa_oER4yILdP6lq5N8sYJM_gBkin2_F99IG7H47UnI6xYcVUBdMkgWuYCxMQ",
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNTU5YzU5MDgzZDc3YWI2NDUxOThiNTIxZmM4ZmVmZmVlZmJkNjIiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoi2YXYrdmF2K8g2LnYp9i02YjYsSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BRWRGVHA1T0ZfbVZMM2EyUWJ0UW5rVkZNd2YzX0tqa1c3UVVPZjEyc1ZubT1zOTYtYyIsImZhbWlseV9pZCI6IjAxR1FEMU1IVzhaOFczUzM3UlQ2UEZaU0daIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL2ZhbWlseWtlZXBlci1tYWluIiwiYXVkIjoiZmFtaWx5a2VlcGVyLW1haW4iLCJhdXRoX3RpbWUiOjE2NzQ0MDE0MzIsInVzZXJfaWQiOiI4QkFIWUNCNEsyWkhob3prUkhYb1ZDajhtZkkzIiwic3ViIjoiOEJBSFlDQjRLMlpIaG96a1JIWG9WQ2o4bWZJMyIsImlhdCI6MTY3NDQwMTQzNCwiZXhwIjoxNjc0NDA1MDM0LCJlbWFpbCI6Im11aG1tZWRhc2hvdXIyMDAwMDAzQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTA2NzIwNzU2Mzg3MTE3MjA2MjUyIl0sImVtYWlsIjpbIm11aG1tZWRhc2hvdXIyMDAwMDAzQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.dOlVjJwm2Xbihd3ficosSsR1fZr-nvoaYIKsJavSAI-wn71VQMf6WX5iBC05uo8xmH8Gqz5MXQuPSseR5Wys0ufvFodwpRLpN5Ua1MMDd9YnkfHq8C96y7NVyJmMHLhng90nZPZpAERUaDW9gmZEHrJm0YkBu2non9oGnpL5L-cHs4rKt6nGc91DCpSjf4DgdvWhsoj5bI1qPHmDlS2A0LtjSa5l9TlXhyFR8fNQ6NEnjPsACKcFL1uY4ZEN1nSMRs1-3TU7q660FnHIhrKFnea3gtc6WingZxSmUDlTXzcTo9M6LqBc2bHL7grYf5nxgDMT1DjiZp1VL5d2ym_dJg",
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNTU5YzU5MDgzZDc3YWI2NDUxOThiNTIxZmM4ZmVmZmVlZmJkNjIiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoi2K3Ys9uM2YYg2KfZhduM2LHbjCIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BRWRGVHA2ZmM2S21hWmk4RVhNZG1rejFBRWJldkVvREkzd1pUODE0UW1YLT1zOTYtYyIsImZhbWlseV9pZCI6IjAxR1FKMlZXQ1hBTk02NFBDQ1pNSlQzUEgyIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL2ZhbWlseWtlZXBlci1tYWluIiwiYXVkIjoiZmFtaWx5a2VlcGVyLW1haW4iLCJhdXRoX3RpbWUiOjE2NzQ1NzA0OTAsInVzZXJfaWQiOiJPM1JRRm1jT1dBT01WQUlraFR5OTJjSjlXTVkyIiwic3ViIjoiTzNSUUZtY09XQU9NVkFJa2hUeTkyY0o5V01ZMiIsImlhdCI6MTY3NDU3MDQ5NywiZXhwIjoxNjc0NTc0MDk3LCJlbWFpbCI6Imhvb3NzZWluLmFtbWlyaWlAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMTE1NTk0MTQwMzM2MTc1MTgyMTAiXSwiZW1haWwiOlsiaG9vc3NlaW4uYW1taXJpaUBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.Da5YlOUz7I-gT0q_vI3WzOH_ip6nFgQhdxoB6oZLHt9juwvefPVYd15jrW8gFJZak9FEFGwKwBGHTSPgyADU1VCmQpbH-m3ycTWvwZXm5ro7oCnIEztoqaEqZx_XA3YNF6Ng4-F32U_IB_WVc8_1bgkEYZwuWkKwLg5TnHJHbVbfGg3iNXZnAivaGngFmEiNeljVP2ziDgUwjCtjfimUY1qXmHMHb1ckNg-y-GJhhQNhJaudErcY4ra4CDp40G_lr6ol93MENu92umGjeOLtN-5HEbBHeP_fToYB3mrDSUf9pjBsv65ifF1XWA-9xiB6H9r74YbyNGF9s0GupD_s4Q",
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQwNTU5YzU5MDgzZDc3YWI2NDUxOThiNTIxZmM4ZmVmZmVlZmJkNjIiLCJ0eXAiOKfZhduM2LHbjCIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BRWRGVHA2ZmM2S21hWmk4RVhNZG1rejFBRWJldkVvREkzd1pUODE0UW1YLT1zOTYtYyIsImZhbWlseV9pZCI6IjAxR1FKMlZXQ1hBTk02NFBDQ1pNSlQzUEgyIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL2ZhbWlseWtlZXBlci1tYWluIiwiYXVkIjoiZmFtaWx5a2VlcGVyLW1haW4iLCJhdXRoX3RpbWUiOjE2NzQ1NzA0OTAsInVzZXJfaWQiOiJPM1JRRm1jT1dBT01WQUlraFR5OTJjSjlXTVkyIiwic3ViIjoiTzNSUUZtY09XQU9NVkFJa2hUeTkyY0o5V01ZMiIsImlhdCI6MTY3NDU3MDQ5NywiZXhwIjoxNjc0NTc0MDk3LCJlbWFpbCI6Imhvb3NzZWluLmFtbWlyaWlAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMTE1NTk0MTQwMzM2MTc1MTgyMTAiXSwiZW1haWwiOlsiaG9vc3NlaW4uYW1taXJpaUBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.Da5YlOUz7I-gT0q_vI3WzOH_ip6nFgQhdxoB6oZLHt9juwvefPVYd15jrW8gFJZak9FEFGwKwBGHTSPgyADU1VCmQpbH-m3ycTWvwZXm5ro7oCnIEztoqaEqZx_XA3YNF6Ng4-F32U_IB_WVc8_1bgkEYZwuWkKwLg5TnHJH"
        )

        tokens.forEachIndexed { index, s ->
            getExpiry(index, s)
        }

        binding.txtShowDialog.setOnClickListener {
            newInstance<TestDialog>().show(supportFragmentManager,"TEST_DIALOG")
        }
    }

    private fun getExpiry(index: Int, jwtToken: String) {
        val isExpired = jwtToken.isTokenExpired()
        Log.d(TAG, "getExpiry: $index $isExpired")
    }
}