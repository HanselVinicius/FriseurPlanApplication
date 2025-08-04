import http from 'k6/http';
import { check, sleep } from "k6";

const target_vus =  5;

export let options = {
    stages: [
        { duration: "5s", target: target_vus },

        { duration: "10s", target: target_vus },

        { duration: "5s", target: 0 }
    ]
};

export default function () {
    const response = http.get("http://host.docker.internal:8080/v1/appointments", {headers: {Accepts: "application/json"}});
    check(response, { "status is 200": (r) => r.status === 200 });
    sleep(.300);
};