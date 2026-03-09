import { useEffect, useRef, useState } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

export function useStompChat(chatRoomId, memberId, memberName, onMessage) {
  const [connected, setConnected] = useState(false);
  const clientRef = useRef(null);

  useEffect(() => {
    if (!chatRoomId) return;

    const wsUrl = `${window.location.protocol === 'https:' ? 'https' : 'http'}://${window.location.host}/ws-connect`;
    const client = new Client({
      webSocketFactory: () => new SockJS(wsUrl),
      onConnect: () => {
        setConnected(true);
        client.subscribe(`/subscribe/chat.${chatRoomId}`, (msg) => {
          const body = JSON.parse(msg.body);
          onMessage(body);
        });
      },
      onDisconnect: () => setConnected(false),
    });
    client.activate();
    clientRef.current = client;
    return () => {
      client.deactivate();
      clientRef.current = null;
    };
  }, [chatRoomId, onMessage]);

  const send = (content) => {
    if (!clientRef.current?.connected) return;
    clientRef.current.publish({
      destination: `/publish/chat.${chatRoomId}`,
      body: JSON.stringify({
        username: memberName,
        content,
        memberId: memberId ?? null,
      }),
    });
  };

  return { connected, send };
}
