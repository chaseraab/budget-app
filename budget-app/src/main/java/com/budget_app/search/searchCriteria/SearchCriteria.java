package com.budget_app.search.searchCriteria;

import com.budget_app.search.SearchOperator;

public record SearchCriteria(
   String field,
   SearchOperator operator,
   Object value
) {}

