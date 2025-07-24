from django.contrib import admin
from django.urls import path, include
from expenses.views import welcome

urlpatterns = [
    path("admin/", admin.site.urls),
    path('api/', include('expenses.urls')),
    path("", welcome, name="welcome"),
]