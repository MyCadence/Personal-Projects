from django.contrib import admin
from .models import Expense  # Import your model

@admin.register(Expense)
class ExpenseAdmin(admin.ModelAdmin):
    list_display = ("description", "amount", "category", "date")  
    search_fields = ("description", "category")  
    list_filter = ("category", "date")  