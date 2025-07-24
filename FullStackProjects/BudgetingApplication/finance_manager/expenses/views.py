from rest_framework import generics
from .models import Expense
from .serializers import ExpenseSerializer
from django.http import HttpResponse

def welcome(request):
    return HttpResponse("Welcome to the Budgeting API. Use 'Your Current URL'/api/expenses/ to access expenses.")

class ExpenseListCreate(generics.ListCreateAPIView):
    queryset = Expense.objects.all()
    serializer_class = ExpenseSerializer

class ExpenseDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Expense.objects.all()
    serializer_class = ExpenseSerializer
