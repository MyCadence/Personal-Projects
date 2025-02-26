from django.db import models

class Expense(models.Model):
    CATEGORY_CHOICES = [
        ('food', 'Food'),
        ('transport', 'Transport'),
        ('entertainment', 'Entertainment'),
        ('utilities', 'Utilities'),
        ('other', 'Other'),
    ]
    
    description = models.CharField(max_length=255)
    amount = models.DecimalField(max_digits=10, decimal_places=2)
    category = models.CharField(max_length=20, choices=CATEGORY_CHOICES, default='other')
    date = models.DateField(auto_now_add=True)

    def __str__(self):
        return f"{self.description} - ${self.amount}"
