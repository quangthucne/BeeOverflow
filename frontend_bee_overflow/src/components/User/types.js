// src/components/types.ts
export interface Image {
  id: number;
  name: string;
}

export interface Answer {
  id: number;
  detail: string;
  isDeleted: boolean | null;
  ansVotes: any[];
  answers: Answer[];
  imagesAns: Image[];
}

export interface Account {
  id: number;
  username: string;
  fullname: string;
  email: string;
  avatar: string;
  phone: string;
  isActive: boolean;
  role: string;
  answers: Answer[];
  gender: number;
}

export interface Tag {
  id: number;
  name: string;
}

export interface QuestionDTO {
  id: number;
  account: Account;
  title: string;
  detail: string;
  isDeleted: boolean;
  isCheck: boolean;
  answers: Answer[];
  questionVotes: any[];
  tags: Tag[];
  imagesQues: Image[];
  createdDate: string | null;
}

export interface Response {
  status: number;
  message: string;
  data: QuestionDTO;
}
