//
//  TalkHeader.swift
//  TalkTalk
//
//  Created by Kacper Grabiec on 03/09/2024.
//

import SwiftUI

struct TalkHeader: View {
    let talkName: String
    let lastMessageSenderName: String
    let lastMessage: String
    let lastMessageDate: Date
    let muted: Bool
    let blocked: Bool
    let unread: Bool
    let talkImageUrl: URL
    
    var body: some View {
        HStack {
            AsyncImage(url: talkImageUrl) { phase in
                if let image = phase.image {
                    image
                } else if phase.error != nil {
                    Color.gray
                } else {
                    ProgressView()
                }
            }.frame(
                width: 48,
                height: 48
            ).clipShape(Circle())
            
            VStack(alignment: .leading) {
                Text(talkName)
                
                Text("\(lastMessageSenderName): \(lastMessage)")
            }
            
            Spacer()
            
            VStack(alignment: .trailing) {
                Text(lastMessageDate.messageDate())
                
                HStack {
                    if muted {
                        Image(systemName: "bell.slash.fill")
                    }
                    
                    if blocked {
                        Image(systemName: "nosign")
                    }
                    
                    if unread {
                        
                    }
                }
            }
        }.frame(maxWidth: .infinity)
            .fontWeight(unread ? .bold : .regular)
    }
}

#Preview {
    TalkHeader(
        talkName: "Kacper Grabiec",
        lastMessageSenderName: "You",
        lastMessage: "Ok",
        lastMessageDate: Date(),
        muted: true,
        blocked: true,
        unread: true,
        talkImageUrl: URL(string: "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA")!
    )
}
