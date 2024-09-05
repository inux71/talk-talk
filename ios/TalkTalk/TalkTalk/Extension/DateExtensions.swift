//
//  DateExtensions.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 03/09/2024.
//

import Foundation

extension Date {
    func messageDate() -> String {
        let now: Date = Date()
        let calendar: Calendar = Calendar.current
        let formatter: DateFormatter = DateFormatter()
        
        if calendar.isDate(self, inSameDayAs: now) {
            formatter.dateFormat = "HH:mm"
        } else if calendar.isDate(self, equalTo: now, toGranularity: .weekOfYear) {
            formatter.dateFormat = "EEEE"
        } else {
            formatter.dateFormat = "dd/MM/yyyy"
        }
        
        return formatter.string(from: self)
    }
}
