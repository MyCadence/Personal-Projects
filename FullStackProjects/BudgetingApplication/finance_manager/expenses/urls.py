from django.urls import path
from .views import ExpenseListCreate, ExpenseDetail, welcome

urlpatterns = [
    path('', welcome, name='welcome'),
    path('expenses/', ExpenseListCreate.as_view(), name='expense-list-create'),
    path('expenses/<int:pk>/', ExpenseDetail.as_view(), name='expense-detail'),  # New URL for single expense
]
