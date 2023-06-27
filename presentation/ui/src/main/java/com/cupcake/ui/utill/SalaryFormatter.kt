package com.cupcake.ui.utill

class SalaryFormatter {
    companion object {
        fun formatSalary(minSalary: Double, maxSalary: Double): String {
            val minSalaryFormatted = formatSalaryValue(minSalary)
            val maxSalaryFormatted = formatSalaryValue(maxSalary)
            return "$minSalaryFormatted-$maxSalaryFormatted USD"
        }

        private fun formatSalaryValue(salary: Double): String {
            val formattedValue: String

            if (salary >= 1000) {
                val valueInK = salary / 1000
                formattedValue = if (valueInK % 1 == 0.0) {
                    "${valueInK.toInt()}k"
                } else {
                    "${valueInK}k"
                }
            } else {
                formattedValue = salary.toInt().toString()
            }

            return formattedValue
        }
    }
}
