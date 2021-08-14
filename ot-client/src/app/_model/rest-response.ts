export class RestResponse {
    constructor(
        public data: Object,
        public message: string,
        public code: number,
        public status: string
    ){}
}